package steps;

import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.InventoryPage;
import pages.CartPage;
import pages.CheckoutPage;

import static org.junit.jupiter.api.Assertions.*;

public class LoginSteps {
    private final WebDriver driver = Hooks.getDriver();
    private final LoginPage loginPage = new LoginPage(driver);
    private final InventoryPage inventoryPage = new InventoryPage(driver);

    @Given("I am on the SauceDemo login page")
    public void iAmOnTheSauceDemoLoginPage() {
        loginPage.open();
    }

    @Given("I am logged in as {string}")
    public void iAmLoggedInAs(String username) {
        loginPage.open();
        loginPage.loginAs(username, "secret_sauce");
    }

    @When("I log in with username {string} and password {string}")
    public void iLogInWithUsernameAndPassword(String username, String password) {
        var user = username.equals("EMPTY") ? "" : username;
        var pass = password.equals("EMPTY") ? "" : password;
        loginPage.loginAs(user, pass);
    }

    @Then("I should see the inventory page")
    public void iShouldSeeTheInventoryPage() {
        assertEquals("Products", inventoryPage.getTitle());
    }

    @Then("the page title should be {string}")
    public void thePageTitleShouldBe(String expectedTitle) {
        assertEquals(expectedTitle, driver.getTitle());
    }

    @Then("the inventory should contain {int} items")
    public void theInventoryShouldContainItems(int expectedCount) {
        assertEquals(expectedCount, inventoryPage.getItemCount());
    }

    @Then("I should see an error message {string}")
    public void iShouldSeeAnErrorMessage(String expectedMessage) {
        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(expectedMessage, loginPage.getErrorMessage());
    }

    @Then("I should see an error message")
    public void iShouldSeeAnErrorMessage() {
        assertTrue(loginPage.isErrorDisplayed(), "Expected error message to be displayed");
    }

    @Then("I should remain on the login page")
    public void iShouldRemainOnTheLoginPage() {
        assertTrue(loginPage.isOnLoginPage());
    }

    @When("I sort products by {string}")
    public void iSortProductsBy(String sortOption) {
        inventoryPage.sortBy(sortOption);
    }

    @Then("the products should be displayed in ascending price order")
    public void theProductsShouldBeDisplayedInAscendingPriceOrder() {
        var prices = inventoryPage.getItemPrices();
        for (int i = 1; i < prices.size(); i++) {
            assertTrue(prices.get(i) >= prices.get(i - 1),
                    "Prices not sorted at index " + i + ": " + prices.get(i - 1) + " > " + prices.get(i));
        }
    }

    @When("I view the inventory page")
    public void iViewTheInventoryPage() {
        assertEquals("Products", inventoryPage.getTitle());
    }

    @Then("some product images should be broken or incorrect")
    public void someProductImagesShouldBeBrokenOrIncorrect() {
        var brokenImages = inventoryPage.getBrokenImageSrc();
        assertTrue(brokenImages.size() > 0, "Expected at least one broken image for problem user");
    }

    @When("I add {string} to the cart")
    public void iAddToCart(String itemName) {
        inventoryPage.addItemToCart(itemName);
    }

    @Given("I have {string} in my cart")
    public void iHaveInMyCart(String itemName) {
        inventoryPage.addItemToCart(itemName);
    }

    @When("I remove {string} from the cart")
    public void iRemoveFromCart(String itemName) {
        inventoryPage.removeItemFromCart(itemName);
    }

    @Then("the cart badge should show {int}")
    public void theCartBadgeShouldShow(int expectedCount) {
        assertEquals(expectedCount, inventoryPage.getCartBadgeCount());
    }

    @Then("the cart badge should not be visible")
    public void theCartBadgeShouldNotBeVisible() {
        assertFalse(inventoryPage.isCartBadgeVisible());
    }

    @When("I navigate to the cart page")
    public void iNavigateToTheCartPage() {
        inventoryPage.goToCart();
    }

    @When("I return to the inventory page")
    public void iReturnToTheInventoryPage() {
        var cartPage = new CartPage(driver);
        cartPage.continueShopping();
    }

    @When("I proceed to checkout")
    public void iProceedToCheckout() {
        inventoryPage.goToCart();
        var cartPage = new CartPage(driver);
        cartPage.proceedToCheckout();
    }

    @When("I enter shipping information {string}, {string}, {string}")
    public void iEnterShippingInformation(String firstName, String lastName, String postalCode) {
        var checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterShippingInfo(firstName, lastName, postalCode);
        checkoutPage.clickContinue();
    }

    @When("I complete the purchase")
    public void iCompleteThePurchase() {
        var checkoutPage = new CheckoutPage(driver);
        checkoutPage.clickFinish();
    }

    @Then("I should see the order confirmation")
    public void iShouldSeeTheOrderConfirmation() {
        var checkoutPage = new CheckoutPage(driver);
        assertTrue(checkoutPage.isOrderComplete());
    }
}
