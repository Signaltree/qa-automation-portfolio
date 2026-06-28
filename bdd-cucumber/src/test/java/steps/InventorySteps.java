package steps;

import hooks.Hooks;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.InventoryPage;

import static org.junit.jupiter.api.Assertions.*;

public class InventorySteps {
    private final WebDriver driver = Hooks.getDriver();
    private final InventoryPage inventoryPage = new InventoryPage(driver);

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
}
