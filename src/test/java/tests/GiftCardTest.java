package tests;

import java.io.IOException;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import pageobjects.CommonElementsPage;
import pageobjects.HomePage;
import resources.Base;

public class GiftCardTest extends Base {
    public WebDriver driver;
    private static final Logger logger = LogManager.getLogger(GiftCardTest.class);

    @Test
    public void giftcard() throws IOException, InterruptedException {
        logger.info("Starting the Gift Card page test");

        driver = initializeBrowser();
        logger.debug("Browser initialized");

        driver.get(prop.getProperty("url"));
        logger.info("Navigated to URL: " + prop.getProperty("url"));

        HomePage homepage = new HomePage(driver);
        CommonElementsPage cm = new CommonElementsPage(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Scroll to the Gift Card link to ensure it is visible
        logger.debug("Scrolling to Gift Card link");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", homepage.giftcardlink());

        // Wait for the Gift Card link to be clickable
        logger.debug("Waiting for the Gift Card link to be clickable");
        wait.until(ExpectedConditions.elementToBeClickable(homepage.giftcardlink()));

        // Click on the Gift Card link
        logger.debug("Clicking on Gift Card link");
        homepage.giftcardlink().click();

        // Wait for the Gift Card page to load and the specific element to be visible
        logger.debug("Waiting for Gift Card page to load");
        wait.until(ExpectedConditions.visibilityOf(cm.giftcard()));

        // Assert if the Gift Card page is displayed
        logger.debug("Asserting if Gift Card page is displayed");
        Assert.assertTrue(cm.giftcard().isDisplayed(), "Gift card element is not displayed");

        logger.info("Gift Card Page test completed successfully");
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            logger.debug("Closing the browser");
            driver.quit();
        }
    }
}
