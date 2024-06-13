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

public class TravelTest extends Base {
    public WebDriver driver;
    private static final Logger logger = LogManager.getLogger(TravelTest.class);

    @Test
    public void travel() throws IOException {
        logger.info("Starting the travel test");

        driver = initializeBrowser();
        logger.debug("Browser initialized");

        driver.get(prop.getProperty("url"));
        logger.info("Navigated to URL: " + prop.getProperty("url"));

        HomePage homepage = new HomePage(driver);
        CommonElementsPage cm = new CommonElementsPage(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        logger.debug("Waiting for the Travel icon to be clickable");
        wait.until(ExpectedConditions.elementToBeClickable(homepage.TravelIcon()));

        logger.debug("Clicking on Travel icon");
        homepage.TravelIcon().click();

        logger.debug("Waiting for the Travel page to be displayed");
        wait.until(ExpectedConditions.visibilityOf(cm.travelpage()));

        logger.debug("Asserting if Travel page is displayed");
        Assert.assertTrue(cm.travelpage().isDisplayed());
        logger.info("Travel test completed successfully");
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            logger.debug("Closing the browser");
            driver.quit();
        }
    }
}
