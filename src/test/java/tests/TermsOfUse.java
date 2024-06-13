package tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import pageobjects.CommonElementsPage;
import pageobjects.HomePage;
import resources.Base;

public class TermsOfUse extends Base{
	
	public WebDriver driver;
    private static final Logger logger = LogManager.getLogger(TermsOfUse.class);
    
    @Test
    public void About() throws IOException, InterruptedException {
    	  logger.info("Starting the Terms of Use test");

          driver = initializeBrowser();
          logger.debug("Browser initialized");

          driver.get(prop.getProperty("url"));
          logger.info("Navigated to URL: " + prop.getProperty("url"));

    	HomePage homePage = new HomePage(driver);
    	CommonElementsPage cmp=new CommonElementsPage(driver);
    	
        logger.debug("Clicking on Terms of Use link");

    	homePage.termsOfUseLink().click();
    	
        logger.debug("Asserting if Terms of Use page is displayed");

    	Assert.assertTrue(cmp.termsHeader().isDisplayed());
    	
    	logger.info("Terms of Use test completed successfully");
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            logger.debug("Closing the browser");
            driver.quit();
        }
    }
}

    	
    	
    	