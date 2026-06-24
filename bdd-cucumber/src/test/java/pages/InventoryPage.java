package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryPage {
    private final WebDriver driver;

    private static final By TITLE = By.cssSelector(".title");
    private static final By INVENTORY_ITEMS = By.cssSelector(".inventory_item");
    private static final By ITEM_NAMES = By.cssSelector(".inventory_item_name");
    private static final By ITEM_PRICES = By.cssSelector(".inventory_item_price");
    private static final By ITEM_IMAGES = By.cssSelector(".inventory_item_img");
    private static final By CART_BADGE = By.cssSelector(".shopping_cart_badge");
    private static final By CART_LINK = By.cssSelector(".shopping_cart_link");
    private static final By SORT_SELECT = By.cssSelector(".product_sort_container");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    public int getItemCount() {
        return driver.findElements(INVENTORY_ITEMS).size();
    }

    public void addItemToCart(String itemName) {
        String xpath = String.format("//div[text()='%s']/ancestor::div[@class='inventory_item']//button[starts-with(@id,'add-to-cart')]", itemName);
        driver.findElement(By.xpath(xpath)).click();
    }

    public void removeItemFromCart(String itemName) {
        String xpath = String.format("//div[text()='%s']/ancestor::div[@class='inventory_item']//button[starts-with(@id,'remove')]", itemName);
        driver.findElement(By.xpath(xpath)).click();
    }

    public int getCartBadgeCount() {
        var badges = driver.findElements(CART_BADGE);
        return badges.isEmpty() ? 0 : Integer.parseInt(badges.get(0).getText());
    }

    public boolean isCartBadgeVisible() {
        return driver.findElements(CART_BADGE).size() > 0;
    }

    public void goToCart() {
        driver.findElement(CART_LINK).click();
    }

    public void sortBy(String sortOption) {
        var select = new Select(driver.findElement(SORT_SELECT));
        select.selectByVisibleText(sortOption);
    }

    public List<String> getItemNames() {
        return driver.findElements(ITEM_NAMES)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<Double> getItemPrices() {
        return driver.findElements(ITEM_PRICES)
                .stream()
                .map(e -> e.getText().replace("$", ""))
                .map(Double::parseDouble)
                .collect(Collectors.toList());
    }

    public List<String> getBrokenImageSrc() {
        var images = driver.findElements(ITEM_IMAGES);
        List<String> broken = new ArrayList<>();
        for (var img : images) {
            var src = img.getAttribute("src");
            if (src == null || src.contains("invalid") || src.contains("broken") || src.isEmpty()) {
                broken.add(src);
            }
        }
        return broken;
    }
}
