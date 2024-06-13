package tests;

import java.io.IOException;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import pageobjects.CommonElementsPage;
import pageobjects.HomePage;
import resources.Base;

public class GroceryTest extends Base {
    public WebDriver driver;
    private static final Logger logger = LogManager.getLogger(GroceryTest.class);

    @Test
    public void grocery() throws IOException {
        logger.info("Starting the Grocery page test");

        driver = initializeBrowser();
        logger.debug("Browser initialized");

        driver.get(prop.getProperty("url"));
        logger.info("Navigated to URL: " + prop.getProperty("url"));

        HomePage homepage = new HomePage(driver);
        CommonElementsPage cm = new CommonElementsPage(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        logger.debug("Waiting for the grocery icon to be clickable");
        wait.until(ExpectedConditions.elementToBeClickable(homepage.groceryIcon()));

        logger.debug("Clicking on grocery icon");
        homepage.groceryIcon().click();

        logger.debug("Waiting for the Grocery page image to be visible");
        wait.until(ExpectedConditions.visibilityOf(cm.groceryPageImage()));

        logger.debug("Asserting if Grocery page is displayed");
        Assert.assertTrue(cm.groceryPageImage().isDisplayed());
        logger.info("Grocery Page test completed successfully");
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            logger.debug("Closing the browser");
            driver.quit();
        }
    }
}
