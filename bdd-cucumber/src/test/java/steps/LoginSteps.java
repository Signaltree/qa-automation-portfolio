package steps;

import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.*;

public class LoginSteps {
    private final WebDriver driver = Hooks.getDriver();
    private final LoginPage loginPage = new LoginPage(driver);

    @Given("I am on the SauceDemo login page")
    public void iAmOnTheSauceDemoLoginPage() {
        loginPage.open();
    }

    @Given("I am logged in as {string}")
    public void iAmLoggedInAs(String username) {
        loginPage.open();
        loginPage.loginAs(username, System.getenv().getOrDefault("PASSWORD", "secret_sauce"));
    }

    @When("I log in with username {string} and password {string}")
    public void iLogInWithUsernameAndPassword(String username, String password) {
        var user = username.equals("EMPTY") ? "" : username;
        var pass = password.equals("EMPTY") ? "" : password;
        loginPage.loginAs(user, pass);
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
}
