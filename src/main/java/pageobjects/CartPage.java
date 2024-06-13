package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

	WebDriver driver;

	public CartPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//button[@class='QqFHMw zA2EfJ _7Pd1Fp']")
	WebElement OrderButton;
	@FindBy(xpath = "//div[normalize-space()='Remove']")
	WebElement RemoveButton;

	@FindBy(css = ".sBxzFz.fF30ZI.A0MXnh")
	WebElement Remove;

	@FindBy(xpath = "//div[@class='krHvwW htEYUF']")
	WebElement RemoveFlyer;

	@FindBy(xpath = "//div[normalize-space()='Save for later']")
	WebElement SaveForLater;
	
	@FindBy(xpath = "//div[@class='s2gOFd']")
    private WebElement cartContent;

	public WebElement OrderButton() {
		return OrderButton;
	}

	public WebElement RemoveButton() {
		return RemoveButton;
	}

	public WebElement Remove() {
		return Remove;
	}

	public WebElement RemoveFlyer() {
		return RemoveFlyer;
	}

	public WebElement SaveForLater() {
		return SaveForLater;
	}
	
	public WebElement cartContent() {
		return cartContent;
	}

}
