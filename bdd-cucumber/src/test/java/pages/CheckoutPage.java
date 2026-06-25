package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    private final WebDriver driver;

    private static final By FIRST_NAME = By.id("first-name");
    private static final By LAST_NAME = By.id("last-name");
    private static final By POSTAL_CODE = By.id("postal-code");
    private static final By CONTINUE_BUTTON = By.id("continue");
    private static final By FINISH_BUTTON = By.id("finish");
    private static final By COMPLETE_HEADER = By.cssSelector(".complete-header");
    private static final By CANCEL_BUTTON = By.id("cancel");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterShippingInfo(String firstName, String lastName, String postalCode) {
        driver.findElement(FIRST_NAME).sendKeys(firstName);
        driver.findElement(LAST_NAME).sendKeys(lastName);
        driver.findElement(POSTAL_CODE).sendKeys(postalCode);
    }

    public void clickContinue() {
        driver.findElement(CONTINUE_BUTTON).click();
    }

    public void clickFinish() {
        driver.findElement(FINISH_BUTTON).click();
    }


    public boolean isOrderComplete() {
        return driver.findElements(COMPLETE_HEADER).size() > 0;
    }
}
