package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Hooks {
    private static WebDriver driver;
    private static final String CHROME_PATH = findChromeBinary();

    private static String findChromeBinary() {
        var candidates = new String[]{
                "/home/signaltree/.cache/ms-playwright/chromium-1228/chrome-linux64/chrome",
                "/usr/bin/google-chrome",
                "/usr/bin/google-chrome-stable",
                "/usr/bin/chromium",
                "/usr/bin/chromium-browser"
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
        if (CHROME_PATH == null) {
            throw new RuntimeException("No Chrome binary found — install Chrome or set CHROME_BIN");
        }

        if (driver == null) {
            WebDriverManager.chromedriver().browserVersion("149").setup();
            var options = new ChromeOptions();
            options.setBinary(CHROME_PATH);
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=1920,1080");
            driver = new ChromeDriver(options);
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
