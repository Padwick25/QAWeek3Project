package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Home {
	WebDriver driver;
	
	@FindBy(css = "#postcode")
	WebElement postcode;
	
	@FindBy(css = "#searchVehiclesMake")
	WebElement make;

	@FindBy(css = "#searchVehiclesModel")
	WebElement model;
	
	@FindBy(css = "#searchVehiclesPriceFrom")
	WebElement minPrice;
	
	@FindBy(css = "#searchVehiclesPriceTo")
	WebElement maxPrice;
	
	@FindBy(css = "#js-search-button")
	WebElement searchButton;
	
	@FindBy(css = "#js-header-nav > ul > li.header__nav-listing.header__nav-my-at > div > a")
	WebElement signInButton;
	
	@FindBy(css = "body > div.is-non-critical.o-clearfix.ov-pos-rel > footer > div > nav:nth-child(4) > ul > li.footer__nav-listing--2 > a > svg > path.nav-social__icon")
	WebElement twitter;
	
	@FindBy(css = "body > main > div > section.sell__nav.t-row.cars > a > figure > div > img")
	WebElement valuationButton;
	
	public Home(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void goSignIn() {
		signInButton.click();
	}
	
	public void search(String postcode, String make, String model) throws InterruptedException {		//add distance, minPrice, and maxPrice if time, currently using default
		this.postcode.sendKeys(postcode);
		this.make.sendKeys(make);
		//this.model.sendKeys(model);
	}
	
	public void clickSearch() {
		this.searchButton.click();
	}
	
	public void twitter() {
		this.twitter.click();
	}
	
	public void goValuation() {
		this.valuationButton.click();
	}
}
