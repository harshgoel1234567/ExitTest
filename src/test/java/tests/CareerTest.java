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

public class CareerTest extends Base {
    public WebDriver driver;
    private static final Logger logger = LogManager.getLogger(CareerTest.class);

    @Test
    public void career() throws IOException {
        logger.info("Starting the Careers page test");

        driver = initializeBrowser();
        logger.debug("Browser initialized");

        driver.get(prop.getProperty("url"));
        logger.info("Navigated to URL: " + prop.getProperty("url"));

        HomePage homepage = new HomePage(driver);
        CommonElementsPage cm = new CommonElementsPage(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        logger.debug("Waiting for the Careers link to be clickable");
        wait.until(ExpectedConditions.elementToBeClickable(homepage.careersLink()));

        logger.debug("Clicking on Careers link");
        homepage.careersLink().click();

        logger.debug("Waiting for the Careers logo to be visible");
        wait.until(ExpectedConditions.visibilityOf(cm.careerLogo()));

        logger.debug("Asserting if Careers page is displayed");
        Assert.assertTrue(cm.careerLogo().isDisplayed());
        logger.info("Careers Page test completed successfully");
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            logger.debug("Closing the browser");
            driver.quit();
        }
    }
}
