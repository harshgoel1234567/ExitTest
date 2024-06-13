package tests;

import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import pageobjects.HomePage;
import pageobjects.ProductPage;
import resources.Base;
import utilities.ExcelUtils;
import utilities.FilePaths;

public class PincodeTest extends Base {
   public WebDriver driver;
    private static final Logger logger = LogManager.getLogger(PincodeTest.class);

    @Test
    public void pincode() throws IOException, InterruptedException {
        String testCaseName = "PincodeTest";
        String excelFilePath = FilePaths.EXCEL_FILE_PATH;
        String sheetName = "Pincode";
        
        if (!ExcelUtils.isExecutionRequired(excelFilePath, sheetName, testCaseName)) {
            logger.info("Skipping test case: " + testCaseName);
            return;
        }

        // Get the search term and pincode from the Excel file
        String searchTerm = ExcelUtils.getTestData(excelFilePath, sheetName, testCaseName, "SearchTerm");
        String pincode = ExcelUtils.getTestData(excelFilePath, sheetName, testCaseName, "Pincode");

        try {
            driver = initializeBrowser();
            driver.get(prop.getProperty("url"));
            logger.debug("Navigated to URL: " + prop.getProperty("url"));

            HomePage homepage = new HomePage(driver);
            ProductPage product = new ProductPage(driver);

            homepage.Searchbar().sendKeys(searchTerm);
            logger.debug("Entered search term: " + searchTerm);
            
            homepage.Search().click();
            logger.debug("Clicked search button");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(product.ProductCard())).click();
            logger.debug("Clicked on product card");

            // Handle window switching
            String originalWindow = driver.getWindowHandle();
            Set<String> allWindows = driver.getWindowHandles();

            for (String windowHandle : allWindows) {
                if (!windowHandle.equals(originalWindow)) {
                    driver.switchTo().window(windowHandle);
                    logger.debug("Switched to product details window");
                    break;
                }
            }

            // Verify product details are displayed in the new window
            Assert.assertTrue(product.Details().isDisplayed());
            logger.info("Product details are displayed");

            product.pincode().sendKeys(pincode);
            logger.debug("Entered pincode: " + pincode);
            
            product.check().click();
            logger.debug("Clicked check button");

            Assert.assertTrue(product.invalid().isDisplayed());
            logger.info("Invalid pincode message is displayed");

        } catch (Exception e) {
            logger.error("Error occurred during the test case execution: " + testCaseName, e);
        } finally {
            if (driver != null) {
                driver.quit();
                logger.debug("Browser closed for test case: " + testCaseName);
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
