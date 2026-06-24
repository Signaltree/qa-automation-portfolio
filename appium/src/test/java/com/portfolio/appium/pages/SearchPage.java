package com.portfolio.appium.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPage extends BasePage {

    @AndroidFindBy(id = "org.wikipedia:id/search_src_text")
    private WebElement searchInput;

    @AndroidFindBy(id = "org.wikipedia:id/page_list_item_title")
    private List<WebElement> searchResults;

    @AndroidFindBy(id = "org.wikipedia:id/page_list_item_description")
    private List<WebElement> searchDescriptions;

    @AndroidFindBy(id = "org.wikipedia:id/search_empty_message")
    private WebElement emptyMessage;

    @AndroidFindBy(className = "android.widget.ImageButton")
    private WebElement backButton;

    public SearchPage(AndroidDriver driver) {
        super(driver);
    }

    public void typeSearch(String query) {
        searchInput.clear();
        searchInput.sendKeys(query);
    }

    public boolean hasResults() {
        return !searchResults.isEmpty();
    }

    public int getResultCount() {
        return searchResults.size();
    }

    public String getFirstResultTitle() {
        if (searchResults.isEmpty()) return "";
        return searchResults.get(0).getText();
    }

    public String getFirstResultDescription() {
        if (searchDescriptions.isEmpty()) return "";
        return searchDescriptions.get(0).getText();
    }

    public void tapFirstResult() {
        if (!searchResults.isEmpty()) {
            searchResults.get(0).click();
        }
    }

    public boolean isEmptyStateVisible() {
        try {
            return emptyMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSearchInputDisplayed() {
        try {
            return searchInput.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
