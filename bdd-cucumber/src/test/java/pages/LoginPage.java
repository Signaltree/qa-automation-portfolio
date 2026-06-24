package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;

    private static final By USERNAME_INPUT = By.id("user-name");
    private static final By PASSWORD_INPUT = By.id("password");
    private static final By LOGIN_BUTTON = By.id("login-button");
    private static final By ERROR_MESSAGE = By.cssSelector("[data-test='error']");
    private static final By ERROR_BUTTON = By.cssSelector(".error-button");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://www.saucedemo.com");
    }

    public void enterUsername(String username) {
        driver.findElement(USERNAME_INPUT).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(LOGIN_BUTTON).click();
    }

    public void loginAs(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }

    public boolean isErrorDisplayed() {
        return driver.findElements(ERROR_MESSAGE).size() > 0;
    }

    public boolean isOnLoginPage() {
        return driver.findElements(LOGIN_BUTTON).size() > 0;
    }
}
