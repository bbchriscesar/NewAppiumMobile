package org.newappium.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import org.newappium.core.DriverManager;
import org.newappium.pages.CountrySelectionPage;
import org.testng.annotations.Test;

public class CountrySelectionTest extends BaseTest {

    @Test
    public void testCountrySelection() {
        try {
            // Get the driver from DriverManager
            CountrySelectionPage countryPage = new CountrySelectionPage((AndroidDriver) DriverManager.getDriver());

            ExtentTest test = extent.createTest("Test Country Selection");
            test.log(Status.INFO, "Opening country dropdown");
            // Open country dropdown
            countryPage.openCountryDropdown();

            test.log(Status.INFO, "Selecting France");
            // Select France
            countryPage.selectCountry("France");

            // Add assertions or further test steps as needed
            System.out.println("Country selection test completed successfully");
            // Add assertions and other test steps
            test.pass("Country selection successful");
        } catch (Exception e) {
            System.out.println("Test failed: " + e.getMessage());
            throw e;
        }
    }
}