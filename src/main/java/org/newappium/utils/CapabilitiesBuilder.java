package org.newappium.utils;

import io.appium.java_client.android.options.UiAutomator2Options;

public class CapabilitiesBuilder {
    public static UiAutomator2Options getUiAutomator2Options() {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setDeviceName(ConfigReader.getProperty("device.name"));
        options.setApp(ConfigReader.getProperty("app.path"));
        options.setAutomationName("UiAutomator2");
        options.setUdid(ConfigReader.getProperty("device.udid"));
        options.setPlatformVersion(ConfigReader.getProperty("platform.version"));
        options.setNoReset(true);
        return options;
    }
}