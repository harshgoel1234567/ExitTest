package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;

	public HomePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//span[normalize-space()='Login']")
	WebElement Login;

	@FindBy(xpath = "//input[@placeholder='Search for Products, Brands and More']")
	WebElement Searchbar;

	@FindBy(xpath = "//button[@title='Search for Products, Brands and More']//*[name()='svg']")
	WebElement Search;

	@FindBy(xpath = "//img[@alt='Dropdown with more help links']")
	WebElement dropDown;

	@FindBy(xpath = "//li[normalize-space()='Notification Preferences']")
	WebElement Notification;

	@FindBy(xpath = "//a[normalize-space()='Terms Of Use']")
	private WebElement termsOfUseLink;
	@FindBy(xpath = "//img[@alt='Grocery']")
	private WebElement groceryIcon;
	@FindBy(xpath = "//img[@alt='Cart']")
	private WebElement cartIcon;
	@FindBy(xpath = "//a[@title='Become a Seller']//img[@alt='Become a Seller']")
	private WebElement becomeSellerLink;
	@FindBy(xpath = "//a[normalize-space()='Contact Us']")
	private WebElement contactUsLink;

	@FindBy(xpath = "//a[normalize-space()='Careers']")
	private WebElement careersLink;
	@FindBy(xpath = "//a[normalize-space()='About Us']")
	private WebElement aboutUsLink;
	@FindBy(xpath = "//a[normalize-space()='Flipkart Stories']")
	private WebElement flipkartStoriesLink;
	@FindBy(xpath = "//a[normalize-space()='Shopsy']")
	private WebElement shopsyLink;
	@FindBy(xpath = "//a[@aria-label='Travel']")
	private WebElement TravelIcon;
	
	@FindBy(css = "img[title='Flipkart']")
	private WebElement homeIcon;
	
	@FindBy(xpath = "//span[normalize-space()='Gift Cards']")
	private WebElement giftcardlink;
	
	 @FindBy(xpath = "//span[text()='Fashion']")
	private WebElement Fashioncat;
	
	

	public WebElement Login() {
		return Login;
	}
	public WebElement Fashioncat() {
		return Fashioncat;
	}

	public WebElement giftcardlink() {
		return giftcardlink;
	}



	public WebElement Searchbar() {
		return Searchbar;
	}

	public WebElement Search() {
		return Search;
	}

	public WebElement dropDown() {
		return dropDown;
	}

	public WebElement Notification() {
		return Notification;
	}

	public WebElement termsOfUseLink() {
		return termsOfUseLink;
	}

	public WebElement groceryIcon() {
		return groceryIcon;
	}

	public WebElement cartIcon() {
		return cartIcon;
	}

	public WebElement becomeSellerLink() {
		return becomeSellerLink;
	}

	public WebElement contactUsLink() {
		return contactUsLink;
	}

	public WebElement careersLink() {
		return careersLink;
	}

	public WebElement aboutUsLink() {
		return aboutUsLink;
	}

	public WebElement flipkartStoriesLink() {
		return flipkartStoriesLink;
	}

	public WebElement shopsyLink() {
		return shopsyLink;
	}

	public WebElement TravelIcon() {
		return TravelIcon;
	}
	public WebElement homeIcon() {
		return homeIcon;
	}

}
