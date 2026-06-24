package com.portfolio.appium.config;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AppiumConfig {

    private static final String DEFAULT_APPIUM_URL = "http://127.0.0.1:4723";
    private static final String DEFAULT_DEVICE_NAME = "Android Emulator";
    private static final String DEFAULT_PLATFORM_VERSION = "15.0";
    private static final String WIKI_PACKAGE = "org.wikipedia";
    private static final String WIKI_ACTIVITY = "org.wikipedia.main.MainActivity";

    public static AndroidDriver createDriver() {
        return createDriver(null);
    }

    public static AndroidDriver createDriver(String appPath) {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setDeviceName(getEnv("APPIUM_DEVICE_NAME", DEFAULT_DEVICE_NAME));
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
        options.setUiautomator2ServerInstallTimeout(java.time.Duration.ofSeconds(30));

        String platformVersion = getEnv("APPIUM_PLATFORM_VERSION", DEFAULT_PLATFORM_VERSION);
        if (platformVersion != null && !platformVersion.isEmpty()) {
            options.setPlatformVersion(platformVersion);
        }

        String apkPath = appPath != null ? appPath : getEnv("APPIUM_APP_PATH", "");
        if (!apkPath.isEmpty()) {
            options.setApp(apkPath);
        } else {
            options.setAppPackage(WIKI_PACKAGE);
            options.setAppActivity(WIKI_ACTIVITY);
        }

        options.setNoReset(false);
        options.setFullReset(false);

        String appiumUrl = getEnv("APPIUM_URL", DEFAULT_APPIUM_URL);
        try {
            return new AndroidDriver(new URL(appiumUrl), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Appium server URL: " + appiumUrl, e);
        }
    }

    private static String getEnv(String key, String fallback) {
        String value = System.getenv(key);
        if (value == null || value.isBlank()) {
            value = System.getProperty(key);
        }
        return value != null && !value.isBlank() ? value : fallback;
    }
}
