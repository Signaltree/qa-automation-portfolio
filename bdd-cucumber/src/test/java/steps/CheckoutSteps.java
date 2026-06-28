package steps;

import hooks.Hooks;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.CartPage;
import pages.CheckoutPage;
import pages.InventoryPage;

import static org.junit.jupiter.api.Assertions.*;

public class CheckoutSteps {
    private final WebDriver driver = Hooks.getDriver();
    private final InventoryPage inventoryPage = new InventoryPage(driver);

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
