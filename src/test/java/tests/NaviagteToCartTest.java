package tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import pageobjects.CartPage;
import pageobjects.HomePage;
import resources.Base;

public class NaviagteToCartTest extends Base {
	public WebDriver driver;
	    private static final Logger logger = LogManager.getLogger(NaviagteToCartTest.class);

	    @Test
	    public void NaviagteToCart() throws IOException {
	        logger.info("Starting the navigate to cart test");

	        driver = initializeBrowser();
	        logger.debug("Browser initialized");

	        driver.get(prop.getProperty("url"));
	        logger.info("Navigated to URL: " + prop.getProperty("url"));

	        HomePage homepage = new HomePage(driver);
	        CartPage cart=new CartPage(driver);

	        logger.debug("Clicking on cart icon");
	        homepage.cartIcon().click();
	        
	        logger.debug("Asserting if cart page is displayed");
	        Assert.assertTrue(cart.cartContent().isDisplayed());
	        logger.info(" Cart Page test completed successfully");
	    }

	    @AfterMethod
	    public void teardown() {
	        if (driver != null) {
	            logger.debug("Closing the browser");
	            driver.quit();
	        }
	    }
	}



