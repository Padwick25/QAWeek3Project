package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Valuation {
	WebDriver driver;
	
	@FindBy(css = "#vrn")
	WebElement regField;
	
	@FindBy(css = "#mileage")
	WebElement mileageField;
	
	@FindBy(css = "body > main > div > section.page-hero > div.content-container > div > form > section > div.car-valuations__input-container.submit-vrm > button")
	WebElement search;
	
	public Valuation(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void getValuation(String reg, String mileage) {
		this.regField.sendKeys(reg);
		this.mileageField.sendKeys(mileage);
		this.search.click();
	}
}
