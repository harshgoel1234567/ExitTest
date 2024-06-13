package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CommonElementsPage {

	WebDriver driver;

	public CommonElementsPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//li[normalize-space()='Desktop Notifications']")
	WebElement DesktopNotification;
	@FindBy(xpath = "//a[normalize-space()='About']")
	private WebElement aboutLink;
	@FindBy(xpath = "//span[@class='logo_career_text']")
	private WebElement careerLogo;
	@FindBy(xpath = "//h1[normalize-space()='Flipkart Help Center | 24x7 Customer Care Support']")
	private WebElement contactHeader;

	@FindBy(xpath = "//img[@class='W5mR4e']")
	WebElement groceryPageImage;
	@FindBy(xpath = "//button[normalize-space()='Start Selling']")
	private WebElement startSellingButton;
	@FindBy(xpath = "//li[@id='menu-item-66351']")
	private WebElement menuItem;
	@FindBy(xpath = "//strong[normalize-space()='Flipkart Terms of Use']")
	private WebElement termsHeader;
	@FindBy(css = "svg[xmlns='http://www.w3.org/2000/svg']")
	private WebElement shopsyElement;
	@FindBy(xpath = "//div[@class='NmD+EK']")
	private WebElement travelpage;

	public WebElement DesktopNotification() {
		return DesktopNotification;
	}

	public WebElement aboutLink() {
		return aboutLink;
	}

	public WebElement careerLogo() {
		return careerLogo;
	}

	public WebElement contactHeader() {
		return contactHeader;
	}

	public WebElement groceryPageImage() {
		return groceryPageImage;
	}

	public WebElement startSellingButton() {
		return startSellingButton;
	}

	public WebElement menuItem() {
		return menuItem;
	}

	public WebElement termsHeader() {
		return termsHeader;
	}

	public WebElement shopsyElement() {
		return shopsyElement;
	}

	public WebElement travelpage() {
		return travelpage;
	}

}
