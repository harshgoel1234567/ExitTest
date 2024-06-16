package tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import pageobjects.CartPage;
import pageobjects.HomePage;
import pageobjects.ProductPage;
import resources.Base;
import utilities.ExcelUtils;
import utilities.FilePaths;

public class RemoveCartTest extends Base {

	public WebDriver driver;
	private static final Logger logger = LogManager.getLogger(RemoveCartTest.class);

	@Test
    public void RemoveCart() throws IOException, InterruptedException {
    	 String excelFilePath = FilePaths.EXCEL_FILE_PATH;
         String sheetName = "CommonCartTests"; 
         
         List<Map<String, String>> testCases = ExcelUtils.getTestCases(excelFilePath, sheetName);
         
         for (Map<String, String> testCase : testCases) {
             String testCaseName = testCase.get("TestCaseName");
             String executionRequired = testCase.get("Execution Required");
             String searchTerm = testCase.get("SearchTerm");
             
             if (executionRequired.equalsIgnoreCase("Yes")) {
            	 logger.info("Executing test case: " + testCaseName);
                 logger.info("Search Term: " + searchTerm);
                 try {
                 
        driver = initializeBrowser();
        driver.get(prop.getProperty("url"));
        logger.debug("Navigated to URL: " + prop.getProperty("url"));

        
        HomePage homepage = new HomePage(driver);
        ProductPage product = new ProductPage(driver);
        CartPage cart = new CartPage(driver);
        
        homepage.Searchbar().sendKeys(searchTerm);
        logger.debug("Entered search term: " + searchTerm);

        homepage.Search().click();
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(product.ProductCard())).click();
        
        // Handle window switching
        String originalWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();

        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        
        // Verify product details are displayed in the new window
        Assert.assertTrue(product.Details().isDisplayed());
        logger.info("Product details are displayed.");

        
        // Scroll the cart button into view and click it
        WebElement cartButton = product.CartButton();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cartButton);
        logger.debug("Scrolled to cart button.");

        // Adding sleep to observe the scrolling action
        Thread.sleep(3000);
        
        // Ensure the element is clickable
        wait.until(ExpectedConditions.elementToBeClickable(cartButton)).click();
        logger.info("Clicked on cart button.");

        
        // Adding sleep to observe the click action
        Thread.sleep(3000);
        
        // Click the remove button
        WebElement removeButton = cart.RemoveButton();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", removeButton);
        wait.until(ExpectedConditions.elementToBeClickable(removeButton)).click();
        logger.info("Clicked on remove button.");

        
        // Click the remove confirmation
        WebElement removeConfirmation = cart.Remove();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", removeConfirmation);
        wait.until(ExpectedConditions.elementToBeClickable(removeConfirmation)).click();
        logger.info("Confirmed remove action.");
        

        
        // Assert the remove flyer is displayed
        WebElement removeFlyer = cart.RemoveFlyer();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", removeFlyer);
        wait.until(ExpectedConditions.visibilityOf(removeFlyer));
        Assert.assertTrue(removeFlyer.isDisplayed());

        // Adding sleep to observe the remove flyer
        Thread.sleep(3000);

        // Switch back to the original window if needed
        driver.switchTo().window(originalWindow);
             }
                 catch (Exception e) {
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
		}
	}
}
