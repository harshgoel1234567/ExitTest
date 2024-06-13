package tests;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import pageobjects.HomePage;
import pageobjects.LoginSignUp;
import resources.Base;
import utilities.ExcelUtils;
import utilities.FilePaths;

public class SignUpTest extends Base {
    public WebDriver driver;
    private static final Logger logger = LogManager.getLogger(SignUpTest.class);

    @Test
    public void signup() throws IOException, InterruptedException {
        String excelFilePath = FilePaths.EXCEL_FILE_PATH;
        String sheetName = "SignUpTest";

        List<Map<String, String>> testCases = ExcelUtils.getTestCases(excelFilePath, sheetName);

        for (Map<String, String> testCase : testCases) {
            String testCaseName = testCase.get("TestCaseName");
            String executionRequired = testCase.get("Execution Required");
            String phone = testCase.get("Phone");

            if (executionRequired.equalsIgnoreCase("Yes")) {
                logger.info("Executing test case: " + testCaseName);
                logger.info("Phone: " + phone);

                try {
                    driver = initializeBrowser();
                    driver.get(prop.getProperty("url"));
                    logger.debug("Navigated to URL: " + prop.getProperty("url"));

                    HomePage homepage = new HomePage(driver);
                    homepage.Login().click();
                    logger.debug("Clicked on Login button");

                    Thread.sleep(3000);

                    LoginSignUp signin = new LoginSignUp(driver);
                    signin.signup().click();
                    logger.debug("Clicked on Sign Up button");

                    signin.emailField().sendKeys(phone);
                    logger.debug("Entered phone number: " + phone);

                    signin.continueButton().click();
                    logger.debug("Clicked on Continue button");

                    Assert.assertTrue(signin.signUpButton().isDisplayed());
                    logger.info("Sign Up button is displayed");
                } catch (Exception e) {
                    logger.error("Error occurred during the test case execution: " + testCaseName, e);
                } finally {
                    if (driver != null) {
                        driver.quit();
                        logger.debug("Browser closed for test case: " + testCaseName);
                    }
                }
            }
        }
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
            logger.debug("Browser closed in teardown method.");
        }
    }
}
