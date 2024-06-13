package tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import pageobjects.HomePage;
import resources.Base;

public class LandingPageTest extends Base {
	
	public WebDriver driver;
	    private static final Logger logger = LogManager.getLogger(LandingPageTest.class);

	    @Test
	    public void LandingPage() throws IOException {
	        logger.info("Starting the Landing Page test");

	        driver = initializeBrowser();
	        logger.debug("Browser initialized");

	        driver.get(prop.getProperty("url"));
	        logger.info("Navigated to URL: " + prop.getProperty("url"));

	        HomePage homepage = new HomePage(driver);
	        
	        
	        logger.debug("Asserting Home page is displayed");
	        Assert.assertTrue(homepage.homeIcon().isDisplayed());
	        logger.info(" Landing Page test completed successfully");
	    }

	    @AfterMethod
	    public void teardown() {
	        if (driver != null) {
	            logger.debug("Closing the browser");
	            driver.quit();
	        }
	    }
	}





	
	
