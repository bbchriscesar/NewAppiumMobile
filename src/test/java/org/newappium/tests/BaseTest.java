package org.newappium.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.appium.java_client.android.AndroidDriver;
import org.newappium.core.DriverManager;
import org.newappium.core.ServerManager;
import org.newappium.utils.AdbCommands;
import org.newappium.utils.CapabilitiesBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class BaseTest {

    protected static ExtentReports extent;
    protected static ExtentTest test;

    @BeforeMethod
    public void setup() throws MalformedURLException {
        // Close app before starting
        AdbCommands.closeECommerceApp();

        // Start server and create driver
        URL serverUrl = ServerManager.startServer();
        AndroidDriver driver = new AndroidDriver(serverUrl,
                CapabilitiesBuilder.getUiAutomator2Options());

        // Add some wait time
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        DriverManager.setDriver(driver);

        // Initialize ExtentReports
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String reportPath = "reports/extent-report_" + timestamp + ".html"; // Unique report filename
        extent = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

        // Add customization options here:
        sparkReporter.config().setReportName("Appium Test Report");
        sparkReporter.config().setDocumentTitle("Test Automation Report");
        sparkReporter.config().setTheme(Theme.DARK); // Or Theme.STANDARD
        sparkReporter.config().setEncoding("utf-8");

        extent.attachReporter(sparkReporter);
    }

    @AfterMethod
    public void tearDown() {
        AndroidDriver driver = (AndroidDriver) DriverManager.getDriver();
        if (driver != null) {
            AdbCommands.closeECommerceApp();
            driver.quit();
            ServerManager.stopServer();
        }
        DriverManager.removeDriver();

        // Flush the ExtentReports
        extent.flush();
    }
}