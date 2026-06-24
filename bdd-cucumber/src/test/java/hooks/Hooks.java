package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.opentest4j.TestAbortedException;

public class Hooks {
    private static WebDriver driver;
    private static final Boolean CHROME_AVAILABLE = checkChrome();

    private static boolean checkChrome() {
        try {
            var process = new ProcessBuilder("which", "google-chrome", "chromium", "chromium-browser")
                    .redirectErrorStream(true)
                    .start();
            int exitCode = process.waitFor();
            return exitCode == 0;
        } catch (Exception e) {
            return false;
        }
    }

    @Before
    public void setUp() {
        if (!CHROME_AVAILABLE) {
            throw new TestAbortedException("Chrome binary not found — skipping browser tests");
        }

        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            var options = new ChromeOptions();
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
