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

public class FlipkartStoriesTest extends Base {
    public WebDriver driver;
    private static final Logger logger = LogManager.getLogger(FlipkartStoriesTest.class);

    @Test
    public void stories() throws IOException {
        logger.info("Starting the Flipkart stories page test");

        driver = initializeBrowser();
        logger.debug("Browser initialized");

        driver.get(prop.getProperty("url"));
        logger.info("Navigated to URL: " + prop.getProperty("url"));

        HomePage homepage = new HomePage(driver);
        CommonElementsPage cm = new CommonElementsPage(driver);

        // Using WebDriverWait with a timeout of 10 seconds
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for Flipkart Stories link to be clickable
        logger.debug("Waiting for Flipkart Stories link to be clickable");
        wait.until(ExpectedConditions.elementToBeClickable(homepage.flipkartStoriesLink()));

        // Click on Flipkart Stories link
        logger.debug("Clicking on Flipkart Stories link");
        homepage.flipkartStoriesLink().click();

        // Wait for menu item (assuming it's an element indicating the page has loaded)
        logger.debug("Waiting for menu item to be displayed");
        wait.until(ExpectedConditions.visibilityOf(cm.menuItem()));

        // Assert that menu item is displayed
        logger.debug("Asserting if Flipkart Stories page is displayed");
        Assert.assertTrue(cm.menuItem().isDisplayed(), "Flipkart Stories page menu item is not displayed");

        logger.info("Flipkart Stories Page test completed successfully");
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            logger.debug("Closing the browser");
            driver.quit();
        }
    }
}
