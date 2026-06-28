package steps;

import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.CartPage;
import pages.InventoryPage;

import static org.junit.jupiter.api.Assertions.*;

public class CartSteps {
    private final WebDriver driver = Hooks.getDriver();
    private final InventoryPage inventoryPage = new InventoryPage(driver);

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
}
