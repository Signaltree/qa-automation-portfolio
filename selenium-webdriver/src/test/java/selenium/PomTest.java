package selenium;

import org.junit.jupiter.api.Test;
import selenium.pages.InventoryPage;
import selenium.pages.LoginPage;

import static org.junit.jupiter.api.Assertions.*;

class PomTest extends BaseTest {

    void freshLogin() {
        new LoginPage(driver).open();
        driver.manage().deleteAllCookies();
        var js = (org.openqa.selenium.JavascriptExecutor) driver;
        js.executeScript("window.localStorage.clear()");
        driver.navigate().refresh();
        new LoginPage(driver).loginAs("standard_user", "secret_sauce");
    }

    @Test
    void testLoginAsStandardUser() {
        freshLogin();
        assertTrue(driver.getCurrentUrl().contains("inventory"));
    }

    @Test
    void testAddItemToCart() {
        freshLogin();
        var inventory = new InventoryPage(driver);
        inventory.addItemToCart("sauce-labs-backpack");
        assertEquals(1, inventory.getCartBadgeCount());
    }

    @Test
    void testAddMultipleItemsAndVerifyCart() {
        freshLogin();
        var inventory = new InventoryPage(driver);
        inventory.addItemToCart("sauce-labs-backpack");
        inventory.addItemToCart("sauce-labs-bike-light");
        assertEquals(2, inventory.getCartBadgeCount());
    }
}
