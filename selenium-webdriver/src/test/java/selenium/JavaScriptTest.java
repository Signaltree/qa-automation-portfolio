package selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class JavaScriptTest extends BaseTest {

    @Test
    void testScrollIntoView() {
        driver.get("https://the-internet.herokuapp.com/large");
        var js = (JavascriptExecutor) driver;
        var el = driver.findElement(By.id("sibling-2.3"));
        js.executeScript("arguments[0].scrollIntoView(true);", el);
        assertTrue(el.isDisplayed());
    }

    @Test
    void testDomModification() {
        driver.get("https://www.saucedemo.com");
        var js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector('.login_logo').style.color = 'red'");
        var color = js.executeScript(
            "return getComputedStyle(document.querySelector('.login_logo')).color"
        ).toString();
        assertTrue(color.contains("255") || color.contains("red"));
    }

    @Test
    void testAsyncScriptExecution() {
        driver.get("https://www.saucedemo.com");
        var js = (JavascriptExecutor) driver;
        var result = js.executeAsyncScript(
            "var cb = arguments[arguments.length - 1];" +
            "setTimeout(function() { cb(document.title); }, 300);"
        );
        assertEquals("Swag Labs", result);
    }

    @Test
    void testGetPageMetrics() {
        driver.get("https://www.saucedemo.com");
        var js = (JavascriptExecutor) driver;
        var title = js.executeScript("return document.title").toString();
        var url = js.executeScript("return document.URL").toString();
        var height = ((Number) js.executeScript("return document.body.scrollHeight")).intValue();
        assertEquals("Swag Labs", title);
        assertTrue(url.contains("saucedemo.com"));
        assertTrue(height > 0);
    }
}
