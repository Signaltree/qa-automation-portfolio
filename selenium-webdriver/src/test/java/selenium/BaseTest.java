package selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;

public class BaseTest {
    protected static WebDriver driver;

    @BeforeAll
    static void setup() {
        WebDriverManager.chromedriver().browserVersion("149").setup();
        var options = new ChromeOptions();
        var chrome = findChromeBinary();
        if (chrome != null) options.setBinary(chrome);
        options.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage", "--window-size=1920,1080");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @AfterAll
    static void teardown() {
        if (driver != null) driver.quit();
    }

    protected static String findChromeBinary() {
        var candidates = new String[]{
            "/usr/bin/google-chrome",
            "/usr/bin/google-chrome-stable",
            "/usr/bin/chromium",
            "/usr/bin/chromium-browser",
            "/home/signaltree/.cache/ms-playwright/chromium-1228/chrome-linux64/chrome"
        };
        for (var p : candidates) {
            if (Files.exists(Paths.get(p))) return p;
        }
        return null;
    }
}
