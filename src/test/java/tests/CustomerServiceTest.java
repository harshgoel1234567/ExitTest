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

public class CustomerServiceTest extends Base {
    public WebDriver driver;
    private static final Logger logger = LogManager.getLogger(CustomerServiceTest.class);

    @Test
    public void contact() throws IOException, InterruptedException {
        logger.info("Starting the Contact Us test");

        driver = initializeBrowser();
        logger.debug("Browser initialized");

        driver.get(prop.getProperty("url"));
        logger.info("Navigated to URL: " + prop.getProperty("url"));

        HomePage homePage = new HomePage(driver);
        CommonElementsPage cmp = new CommonElementsPage(driver);

        // Using WebDriverWait with a timeout of 10 seconds
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for Contact Us link to be clickable
        logger.debug("Waiting for Contact Us link to be clickable");
        wait.until(ExpectedConditions.elementToBeClickable(homePage.contactUsLink()));

        // Click on Contact Us link
        logger.debug("Clicking on Contact Us link");
        homePage.contactUsLink().click();

        // Wait for Contact Us page header to be visible
        logger.debug("Waiting for Contact Us page header to be visible");
        wait.until(ExpectedConditions.visibilityOf(cmp.contactHeader()));

        // Assert that Contact Us page header is displayed
        logger.debug("Asserting if Contact Us page is displayed");
        Assert.assertTrue(cmp.contactHeader().isDisplayed(), "Contact Us page header is not displayed");

        logger.info("Contact Us test completed successfully");
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            logger.debug("Closing the browser");
            driver.quit();
        }
    }
}
