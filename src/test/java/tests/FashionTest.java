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

public class FashionTest extends Base {

	public WebDriver driver;
	private static final Logger logger = LogManager.getLogger(BecomeASellerTest.class);

	@Test
	public void fashion() throws IOException {
		logger.info("Starting the Fashion category test");

		driver = initializeBrowser();
		logger.debug("Browser initialized");

		driver.get(prop.getProperty("url"));
		logger.info("Navigated to URL: " + prop.getProperty("url"));

		HomePage homepage = new HomePage(driver);
		CommonElementsPage cm = new CommonElementsPage(driver);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		logger.debug("Waiting for the Fashion link to be clickable");
		wait.until(ExpectedConditions.elementToBeClickable(homepage.Fashioncat()));

		logger.debug("Clicking on fashion link");
		homepage.Fashioncat().click();

		logger.debug("Waiting for the fashion to be visible");
		wait.until(ExpectedConditions.visibilityOf(cm.fashion()));

		logger.debug("Asserting if fashion page is displayed");
		Assert.assertTrue(cm.fashion().isDisplayed());
		logger.info("Fashion category test completed successfully");
	}

	@AfterMethod
	public void teardown() {
		if (driver != null) {
			logger.debug("Closing the browser");
			driver.quit();
		}
	}
}
