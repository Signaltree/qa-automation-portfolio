package com.portfolio.appium.tests;

import com.portfolio.appium.config.AppiumConfig;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest {
    protected AndroidDriver driver;

    @BeforeMethod(alwaysRun = true)
    @Parameters({"appPath"})
    public void setUp(String appPath) {
        String path = appPath != null && !appPath.isEmpty() && !appPath.equals("null")
                ? appPath : null;
        driver = AppiumConfig.createDriver(path);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception ignored) {
            }
        }
    }
}
