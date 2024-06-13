package tests;

import java.io.IOException;
import java.time.Duration;
import java.util.Set;

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

public class NewSiteShopsyTest extends Base {
    
   public WebDriver driver;
    private static final Logger logger = LogManager.getLogger(NewSiteShopsyTest.class);

    @Test
    public void shopsy() throws IOException {
        logger.info("Starting the Shopsy site test");

        driver = initializeBrowser();
        logger.debug("Browser initialized");

        driver.get(prop.getProperty("url"));
        logger.info("Navigated to URL: " + prop.getProperty("url"));

        HomePage homepage = new HomePage(driver);
        CommonElementsPage cm = new CommonElementsPage(driver);

        logger.debug("Clicking on shopsy link");
        homepage.shopsyLink().click();
        
        logger.debug("Switching window");

        String originalWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();

        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(cm.shopsyElement()));

        Assert.assertTrue(cm.shopsyElement().isDisplayed());
        logger.info("Shopsy element is displayed, test passed");
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            logger.debug("Closing the browser");
            driver.quit();
        }
    }
}
