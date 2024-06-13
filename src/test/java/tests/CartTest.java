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

public class CartTest extends Base {
    
   public WebDriver driver;
    private static final Logger logger = LogManager.getLogger(CartTest.class);
    
    @Test
    public void AddToCart() throws IOException, InterruptedException {
        
        String excelFilePath = FilePaths.EXCEL_FILE_PATH;
        String sheetName = "AddToCart";
        
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
        
                    // Scroll the cart button into view and click it
                    WebElement cartButton = product.CartButton();
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cartButton);
                    logger.debug("Scrolled to cart button");
        
                    Thread.sleep(3000);
                    wait.until(ExpectedConditions.elementToBeClickable(cartButton)).click();
                    logger.debug("Clicked cart button");
        
                    Thread.sleep(3000);
        
                    // Wait for the order button to be visible
                    wait.until(ExpectedConditions.visibilityOf(cart.OrderButton()));
                    logger.debug("Order button is visible");
                    
                    // Verify the order button is displayed
                    Assert.assertTrue(cart.OrderButton().isDisplayed());
                    logger.info("Order button is displayed");
                    
                    Thread.sleep(3000);
        
                    // Switch back to the original window if needed
                    driver.switchTo().window(originalWindow);
                    logger.debug("Switched back to original window");

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
