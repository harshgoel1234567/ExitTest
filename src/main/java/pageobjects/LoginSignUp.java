package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginSignUp {

	WebDriver driver;

	public LoginSignUp(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = "input[class='r4vIwl BV+Dqf']")
	WebElement emailField;
	@FindBy(xpath = "//button[normalize-space()='Request OTP']")
	WebElement otpButton;
	@FindBy(xpath = "//button[normalize-space()='Verify']")
	WebElement verifyButton;
	@FindBy(xpath = "(//a[normalize-space()='New to Flipkart? Create an account'])[1]")
	WebElement signup;

	@FindBy(css = "button[class='QqFHMw twnTnD _7Pd1Fp']")
	WebElement continueButton;
	@FindBy(css = "button[class='QqFHMw twnTnD _7Pd1Fp']")
	WebElement signUpButton;

	public WebElement emailField() {
		return emailField;
	}

	public WebElement otpButton() {
		return otpButton;
	}

	public WebElement verifyButton() {
		return verifyButton;
	}

	public WebElement signup() {
		return signup;
	}

	public WebElement continueButton() {
		return continueButton;
	}

	public WebElement signUpButton() {
		return signUpButton;
	}

}
