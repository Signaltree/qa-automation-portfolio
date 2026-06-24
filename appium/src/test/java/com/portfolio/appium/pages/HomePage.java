package com.portfolio.appium.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

    @AndroidFindBy(accessibility = "Search Wikipedia")
    private WebElement searchField;

    @AndroidFindBy(id = "org.wikipedia:id/search_src_text")
    private WebElement searchInput;

    @AndroidFindBy(id = "org.wikipedia:id/view_announcement_header_image")
    private WebElement onboardingImage;

    @AndroidFindBy(id = "org.wikipedia:id/fragment_onboarding_skip_button")
    private WebElement skipOnboarding;

    public HomePage(AndroidDriver driver) {
        super(driver);
    }

    public boolean isOnboardingVisible() {
        try {
            return onboardingImage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void skipOnboardingIfPresent() {
        if (isOnboardingVisible()) {
            skipOnboarding.click();
        }
    }

    public void tapSearch() {
        searchField.click();
    }

    public void search(String query) {
        tapSearch();
        searchInput.sendKeys(query);
    }

    public String getSearchPlaceholder() {
        return searchField.getText();
    }
}
