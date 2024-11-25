package org.newappium.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.newappium.core.DriverManager;
import org.newappium.utils.Actions;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    protected AppiumDriver driver;
    protected Actions actions;

    public BasePage(AndroidDriver driver) {
        this.driver = DriverManager.getDriver();
        this.actions = new Actions();
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }

    protected void scrollToElement(String text) {
        actions.scrollToText(text);
    }
}