package org.newappium.utils;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import org.newappium.core.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;

public class Actions {
    private final AppiumDriver driver;
    private final WebDriverWait wait;
    private static final Duration TIMEOUT = Duration.ofSeconds(10);

    public Actions() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, TIMEOUT);
    }

    public void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public void sendKeys(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    public String getText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    public boolean isElementPresent(By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void waitForElementToDisappear(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void scrollToText(String text) {
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().textContains(\"" + text + "\"))"));
    }

    public void swipe(int startX, int startY, int endX, int endY) {
        // Implement swipe using W3C Actions
        new io.appium.java_client.TouchAction((PerformsTouchActions) driver)
                .press(point(startX, startY))
                .waitAction(waitOptions(Duration.ofMillis(1000)))
                .moveTo(point(endX, endY))
                .release()
                .perform();
    }

    public void tapByCoordinates(int x, int y) {
        new io.appium.java_client.TouchAction((PerformsTouchActions) driver)
                .tap(point(x, y))
                .perform();
    }
}