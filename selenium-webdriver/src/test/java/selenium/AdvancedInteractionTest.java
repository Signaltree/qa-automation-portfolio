package selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class AdvancedInteractionTest extends BaseTest {

    @Test
    void testDragAndDrop() {
        driver.get("https://the-internet.herokuapp.com/drag_and_drop");
        var a = driver.findElement(By.id("column-a"));
        var b = driver.findElement(By.id("column-b"));
        new Actions(driver).dragAndDrop(a, b).perform();
        assertEquals("B", driver.findElement(By.id("column-a")).getText());
    }

    @Test
    void testHoverAndVerifyCaption() {
        driver.get("https://the-internet.herokuapp.com/hovers");
        var avatar = driver.findElement(By.cssSelector(".figure:nth-child(3) img"));
        new Actions(driver).moveToElement(avatar).perform();
        var caption = driver.findElement(By.cssSelector(".figure:nth-child(3) .figcaption h5"));
        assertTrue(caption.isDisplayed());
        assertTrue(caption.getText().contains("name: user1"));
    }

    @Test
    void testKeyboardActions() {
        driver.get("https://the-internet.herokuapp.com/key_presses");
        new Actions(driver).sendKeys("A").perform();
        var result = driver.findElement(By.id("result"));
        assertEquals("You entered: A", result.getText());
    }

    @Test
    void testAlertHandling() {
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        driver.findElement(By.cssSelector("button[onclick='jsAlert()']")).click();
        var alert = driver.switchTo().alert();
        assertEquals("I am a JS Alert", alert.getText());
        alert.accept();
        assertEquals("You successfully clicked an alert", driver.findElement(By.id("result")).getText());

        driver.findElement(By.cssSelector("button[onclick='jsConfirm()']")).click();
        driver.switchTo().alert().dismiss();
        assertEquals("You clicked: Cancel", driver.findElement(By.id("result")).getText());

        driver.findElement(By.cssSelector("button[onclick='jsPrompt()']")).click();
        alert = driver.switchTo().alert();
        alert.sendKeys("hello");
        alert.accept();
        assertEquals("You entered: hello", driver.findElement(By.id("result")).getText());
    }
}
