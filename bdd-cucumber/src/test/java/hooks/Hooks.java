package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Hooks {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final String CHROME_PATH = findChromeBinary();
    private static final String REMOTE_URL = System.getProperty("selenium.remote.url");

    private static String findChromeBinary() {
        var candidates = new String[]{
                "/usr/bin/google-chrome",
                "/usr/bin/google-chrome-stable",
                "/usr/bin/chromium",
                "/usr/bin/chromium-browser",
                "/home/signaltree/.cache/ms-playwright/chromium-1228/chrome-linux64/chrome"
        };
        for (String path : candidates) {
            if (Files.exists(Paths.get(path))) {
                return path;
            }
        }
        return null;
    }

    @Before
    public void setUp() {
        getDriver();
    }

    @After
    public void tearDown() {
        var d = driver.get();
        if (d != null) {
            d.quit();
            driver.remove();
        }
    }

    public static WebDriver getDriver() {
        var d = driver.get();
        if (d == null) {
            d = createDriver();
            driver.set(d);
        }
        return d;
    }

    private static WebDriver createDriver() {
        if (REMOTE_URL != null) {
            try {
                var options = new ChromeOptions();
                options.addArguments("--headless=new");
                return new RemoteWebDriver(new URL(REMOTE_URL), options);
            } catch (Exception e) {
                throw new RuntimeException("Failed to create remote driver", e);
            }
        }

        if (CHROME_PATH == null) {
            throw new RuntimeException("No Chrome binary found — install Chrome or set CHROME_BIN");
        }

        WebDriverManager.chromedriver().browserVersion("149").setup();
        var options = new ChromeOptions();
        options.setBinary(CHROME_PATH);
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");
        return new ChromeDriver(options);
    }
}
