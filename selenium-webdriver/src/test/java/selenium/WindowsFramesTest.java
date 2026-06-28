package selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class WindowsFramesTest extends BaseTest {

    @Test
    void testSwitchBetweenWindows() {
        driver.get("https://the-internet.herokuapp.com/windows");
        var mainHandle = driver.getWindowHandle();
        driver.findElement(By.cssSelector(".example a")).click();
        var allHandles = driver.getWindowHandles();
        allHandles.remove(mainHandle);
        var newHandle = allHandles.iterator().next();
        driver.switchTo().window(newHandle);
        var wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        var heading = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h3")));
        assertEquals("New Window", heading.getText());
        driver.close();
        driver.switchTo().window(mainHandle);
        assertEquals("Opening a new window", driver.findElement(By.cssSelector("h3")).getText());
    }

    @Test
    void testSwitchToFrame() {
        driver.get("https://the-internet.herokuapp.com/iframe");
        driver.switchTo().frame("mce_0_ifr");
        var body = driver.findElement(By.id("tinymce"));
        var js = (org.openqa.selenium.JavascriptExecutor) driver;
        js.executeScript("arguments[0].innerHTML = 'Selenium WebDriver test';", body);
        assertEquals("Selenium WebDriver test", body.getText());
    }

    @Test
    void testOpenTabViaJsAndSwitch() {
        driver.get("https://www.saucedemo.com");
        var mainHandle = driver.getWindowHandle();
        var js = (org.openqa.selenium.JavascriptExecutor) driver;
        js.executeScript("window.open('https://the-internet.herokuapp.com', '_blank')");
        var handles = driver.getWindowHandles();
        handles.remove(mainHandle);
        var tabHandle = handles.iterator().next();
        driver.switchTo().window(tabHandle);
        var wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(d -> !d.getTitle().isEmpty());
        assertEquals("The Internet", driver.getTitle());
        driver.close();
        driver.switchTo().window(mainHandle);
        assertEquals("Swag Labs", driver.getTitle());
    }

    @Test
    void testNestedFrames() {
        driver.get("https://the-internet.herokuapp.com/nested_frames");
        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-left");
        assertEquals("LEFT", driver.findElement(By.tagName("body")).getText());
        driver.switchTo().parentFrame();
        driver.switchTo().frame("frame-middle");
        assertEquals("MIDDLE", driver.findElement(By.id("content")).getText());
        driver.switchTo().defaultContent();
        driver.switchTo().frame("frame-bottom");
        assertEquals("BOTTOM", driver.findElement(By.tagName("body")).getText());
    }
}
