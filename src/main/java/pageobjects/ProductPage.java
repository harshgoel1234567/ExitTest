package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {

	WebDriver driver;

	public ProductPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//body[1]/div[1]/div[1]/div[3]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/a[1]/div[2]/div[1]")
	WebElement ProductCard;

	@FindBy(xpath = "//button[normalize-space()='Buy Now']")
	WebElement Details;

	@FindBy(xpath = "//span[@class='BUOuZu']")
	WebElement searchRes;

	@FindBy(xpath = "//button[normalize-space()='Add to cart']")
	WebElement CartButton;

	@FindBy(xpath = "//input[@id='pincodeInputId']")
	WebElement pincode;

	public WebElement ProductCard() {
		return ProductCard;
	}

	public WebElement Details() {
		return Details;
	}

	public WebElement searchRes() {
		return searchRes;
	}

	public WebElement CartButton() {
		return CartButton;
	}

	public WebElement pincode() {
		return pincode;
	}

	@FindBy(css = ".i40dM4")
	WebElement check;

	public WebElement check() {
		return check;
	}

	@FindBy(css = ".nyRpc8")
	WebElement invalid;

	public WebElement invalid() {
		return invalid;
	}

}
