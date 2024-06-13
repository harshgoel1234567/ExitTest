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

public class AboutTest extends Base {

    public WebDriver driver;
    private static final Logger logger = LogManager.getLogger(AboutTest.class);

    @Test
    public void About() throws IOException, InterruptedException {
        logger.info("Starting the About Us test");

        driver = initializeBrowser();
        logger.debug("Browser initialized");

        driver.get(prop.getProperty("url"));
        logger.info("Navigated to URL: " + prop.getProperty("url"));

        HomePage homePage = new HomePage(driver);
        CommonElementsPage cmp = new CommonElementsPage(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        logger.debug("Waiting for the About Us link to be clickable");
        wait.until(ExpectedConditions.elementToBeClickable(homePage.aboutUsLink()));

        logger.debug("Clicking on About Us link");
        homePage.aboutUsLink().click();

        logger.debug("Waiting for the About Us page to be displayed");
        wait.until(ExpectedConditions.visibilityOf(cmp.aboutLink()));

        logger.debug("Asserting if About Us page is displayed");
        Assert.assertTrue(cmp.aboutLink().isDisplayed());

        logger.info("About Us test completed successfully");
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            logger.debug("Closing the browser");
            driver.quit();
        }
    }
}
