package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignIn {
	private WebDriver driver;
	
	@FindBy(css = "#signin-email")
	private WebElement emailField;
	
	@FindBy(css = "#signin-password")
	private WebElement passwordField;
	
	@FindBy(css = "#signInSubmit")
	private WebElement signInButton;
	
	public SignIn(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void attemptSignIn(String email, String password) {
		this.emailField.sendKeys(email);
		this.passwordField.sendKeys(password);
		signInButton.click();
	}
}
