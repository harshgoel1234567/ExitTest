package listeners;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.Base;
import utilities.ExtentReporter;

public class Listeners extends Base implements ITestListener {
    ExtentReports extentReport = ExtentReporter.getExtentReport();
    WebDriver driver = null;
    ExtentTest extentTest;

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getName();
        extentTest = extentReport.createTest(testName + " test execution begins ");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getName();
        extentTest.log(Status.PASS, testName + " is passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getName();
        extentTest.fail(result.getThrowable());

        String testMethodName = result.getName(); // getting test name

        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            extentTest.fail("Failed to retrieve WebDriver instance");
            e.printStackTrace();
        }

        if (driver != null) {
            try {
                String screenshotPath = takeScreenshot(testMethodName, driver);
                extentTest.addScreenCaptureFromPath(screenshotPath, testMethodName);
            } catch (IOException e) {
                extentTest.fail("Failed to take screenshot");
                e.printStackTrace();
            }
        } else {
            extentTest.fail("WebDriver instance is null, unable to take screenshot");
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReport.flush(); // if not written nothing will happen
    }
}
