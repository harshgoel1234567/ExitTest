package tests;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import pageobjects.HomePage;
import pageobjects.LoginSignUp;
import resources.Base;

public class LoginTest extends Base {
	public WebDriver driver;

	@Test
	public void login() throws IOException {
		driver = initializeBrowser();
		driver.get(prop.getProperty("url"));

		HomePage homepage = new HomePage(driver);
		homepage.Login().click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		LoginSignUp login = new LoginSignUp(driver);

		// Use explicit wait instead of Thread.sleep
		wait.until(ExpectedConditions.visibilityOf(login.emailField()));
		login.emailField().sendKeys("9140865795");

		wait.until(ExpectedConditions.elementToBeClickable(login.otpButton()));
		login.otpButton().click();

		// Wait until the verify button is visible
		wait.until(ExpectedConditions.visibilityOf(login.verifyButton()));

		Assert.assertTrue(login.verifyButton().isDisplayed());
	}

	@AfterMethod
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
