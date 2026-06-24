package com.portfolio.appium.tests;

import com.portfolio.appium.pages.HomePage;
import com.portfolio.appium.pages.SearchPage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

@Feature("Wikipedia Search")
public class WikipediaSearchTest extends BaseTest {

    @Test
    @Story("Basic search returns results")
    @Description("Search for 'Selenium' and verify results are displayed")
    public void testSearchReturnsResults() {
        HomePage home = new HomePage(driver);
        home.skipOnboardingIfPresent();

        SearchPage search = new SearchPage(driver);
        home.search("Selenium");

        Assert.assertTrue(search.hasResults(), "Search should return results");
        Assert.assertTrue(search.getResultCount() > 0, "Result count should be positive");
    }

    @Test
    @Story("Search result contains expected title")
    @Description("Search for 'Appium' and verify first result title")
    public void testSearchResultTitle() {
        HomePage home = new HomePage(driver);
        home.skipOnboardingIfPresent();

        SearchPage search = new SearchPage(driver);
        home.search("Appium");

        Assert.assertTrue(search.hasResults(), "Search should return results");
        String title = search.getFirstResultTitle();
        Assert.assertTrue(title.toLowerCase().contains("appium"),
                "First result should contain 'Appium'. Got: " + title);
    }

    @Test
    @Story("Search result has description")
    @Description("Search for 'Testing' and verify first result has description text")
    public void testSearchResultHasDescription() {
        HomePage home = new HomePage(driver);
        home.skipOnboardingIfPresent();

        SearchPage search = new SearchPage(driver);
        home.search("Testing");

        Assert.assertTrue(search.hasResults(), "Search should return results");
        String desc = search.getFirstResultDescription();
        Assert.assertNotNull(desc, "Description should not be null");
        Assert.assertFalse(desc.isEmpty(), "Description should not be empty");
    }

    @Test
    @Story("Search input is accessible")
    @Description("Verify the search input field is present and interactable")
    public void testSearchInputAccessible() {
        HomePage home = new HomePage(driver);
        home.skipOnboardingIfPresent();
        home.tapSearch();

        SearchPage search = new SearchPage(driver);
        Assert.assertTrue(search.isSearchInputDisplayed(), "Search input should be displayed after tapping");
    }
}
