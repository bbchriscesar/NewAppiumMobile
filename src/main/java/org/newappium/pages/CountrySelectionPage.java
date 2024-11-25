package org.newappium.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class CountrySelectionPage extends BasePage {
    private final By countryDropdown = AppiumBy.id("com.androidsample.generalstore:id/spinnerCountry");

    public CountrySelectionPage(AndroidDriver driver) {
        super(driver);
    }

    public void openCountryDropdown() {
        actions.click(countryDropdown);
    }

    public void selectCountry(String countryName) {
        actions.scrollToText(countryName);
        By countryOption = AppiumBy.xpath(
                "//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"" + countryName + "\"]");
        actions.click(countryOption);
    }
}