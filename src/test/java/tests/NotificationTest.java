package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pageobjects.CommonElementsPage;
import pageobjects.HomePage;
import resources.Base;

public class NotificationTest extends Base {

   public WebDriver driver;
    private static final Logger logger = LogManager.getLogger(NotificationTest.class);

    @Test
    public void notification() throws IOException {
        logger.info("Starting the notification test");

        driver = initializeBrowser();
        logger.debug("Browser initialized");

        driver.get(prop.getProperty("url"));
        logger.info("Navigated to URL: " + prop.getProperty("url"));

        HomePage homepage = new HomePage(driver);
        CommonElementsPage cm = new CommonElementsPage(driver);

        logger.debug("Clicking on dropdown");
        homepage.dropDown().click();

        logger.debug("Clicking on Notification");
        homepage.Notification().click();

        logger.debug("Asserting if Desktop Notification is displayed");
        Assert.assertTrue(cm.DesktopNotification().isDisplayed());

        logger.info("Notification test completed successfully");
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            logger.debug("Closing the browser");
            driver.quit();
        }
    }
}
