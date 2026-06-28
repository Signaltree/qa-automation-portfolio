package selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class MultiBrowserTest {

    private WebDriver driver;

    @AfterEach
    void teardown() {
        if (driver != null) driver.quit();
    }

    @Test
    void testChromeHeadless() {
        WebDriverManager.chromedriver().browserVersion("149").setup();
        var options = new ChromeOptions();
        var chrome = findChrome();
        if (chrome != null) options.setBinary(chrome);
        options.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get("https://www.saucedemo.com");
        assertEquals("Swag Labs", driver.getTitle());
    }

    @Test
    void testFirefoxHeadless() {
        var firefox = findBinary("firefox");
        assumeTrue(firefox != null, "Firefox not installed");
        WebDriverManager.firefoxdriver().setup();
        var options = new FirefoxOptions();
        options.setBinary(firefox);
        options.addArguments("--headless");
        driver = new FirefoxDriver(options);
        driver.get("https://www.saucedemo.com");
        assertEquals("Swag Labs", driver.getTitle());
    }

    @Test
    void testEdgeHeadless() {
        var edge = findBinary("edge");
        assumeTrue(edge != null, "Edge not installed");
        WebDriverManager.edgedriver().setup();
        var options = new EdgeOptions();
        options.setBinary(edge);
        options.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage");
        driver = new EdgeDriver(options);
        driver.get("https://www.saucedemo.com");
        assertEquals("Swag Labs", driver.getTitle());
    }

    private String findChrome() {
        var candidates = new String[]{
            "/usr/bin/google-chrome",
            "/usr/bin/google-chrome-stable",
            "/usr/bin/chromium",
            "/usr/bin/chromium-browser",
            "/home/signaltree/.cache/ms-playwright/chromium-1228/chrome-linux64/chrome"
        };
        return findBinary(candidates);
    }

    private String findBinary(String name) {
        try {
            var proc = Runtime.getRuntime().exec(new String[]{"which", name});
            if (proc.waitFor() == 0) {
                return new String(proc.getInputStream().readAllBytes()).strip();
            }
        } catch (Exception ignored) {}
        return null;
    }

    private String findBinary(String[] candidates) {
        for (var p : candidates) {
            if (java.nio.file.Files.exists(java.nio.file.Paths.get(p))) return p;
        }
        return null;
    }
}
