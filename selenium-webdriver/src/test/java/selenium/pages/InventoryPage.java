package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {
    private final WebDriver driver;
    private static final By CART_BADGE = By.cssSelector(".shopping_cart_badge");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addItemToCart(String itemId) {
        driver.findElement(By.id("add-to-cart-" + itemId)).click();
    }

    public int getCartBadgeCount() {
        var els = driver.findElements(CART_BADGE);
        if (els.isEmpty()) return 0;
        return Integer.parseInt(els.get(0).getText());
    }
}
