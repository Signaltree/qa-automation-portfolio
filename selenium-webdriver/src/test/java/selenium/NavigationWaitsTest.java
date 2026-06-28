package selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class NavigationWaitsTest extends BaseTest {

    @Test
    void testNavigateAndVerifyTitle() {
        driver.get("https://www.saucedemo.com");
        assertTrue(driver.getTitle().contains("Swag Labs"));
    }

    @Test
    void testExplicitWaitForElement() {
        driver.get("https://www.saucedemo.com");
        var wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        var loginBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("login-button")));
        assertTrue(loginBtn.isDisplayed());
    }

    @Test
    void testImplicitWaitCatchesLateElement() {
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
        driver.findElement(By.cssSelector("button")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        var text = driver.findElement(By.id("finish")).getText();
        assertEquals("Hello World!", text);
    }

    @Test
    void testFluentWaitWithPolling() {
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
        driver.findElement(By.cssSelector("#start button")).click();
        var wait = new FluentWait<>(driver)
            .withTimeout(Duration.ofSeconds(8))
            .pollingEvery(Duration.ofMillis(300))
            .ignoring(NoSuchElementException.class);
        var el = wait.until(d -> d.findElement(By.id("finish")));
        assertEquals("Hello World!", el.getText());
    }
}
