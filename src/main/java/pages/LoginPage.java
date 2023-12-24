package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.ControlActions;

public class LoginPage extends ControlActions {

	@FindBy(xpath="//input[@id='userEmail']")
	WebElement userEmailElement;
	
	@FindBy(xpath="//input[@id='userPassword']")
	WebElement userPasswordElement;
	
	@FindBy(xpath="//input[@id='login']")
	WebElement loginButtonElement;
	
	@FindBy(xpath="//div[@aria-label='Login Successfully']")
	WebElement loginSuccessfulElement;
	
	@FindBy(xpath="//div[@aria-label='Incorrect email or password.']")
	WebElement loginUnsuccessfulElement;
	
	@FindBy(xpath="//div[text()='*Email is required']")
	WebElement emailIsRequiredElement;
	
	@FindBy(xpath="//div[text()='*Password is required']")
	WebElement passwordIsRequired;
	
	public LoginPage(){
		PageFactory.initElements(driver, this);
	}
	
	public DashBoardPage login(String email, String password) {
		enterUserEmail(email);
		enterPassword(password);
		return clickOnLoginButton();
	}
	
	public void enterUserEmail(String email) {
		System.out.println("Enter email");
		userEmailElement.sendKeys(email);
	}
	
	public void enterPassword(String password) {
		System.out.println("Enter password");
		userPasswordElement.sendKeys(password);
	}
	
	public DashBoardPage clickOnLoginButton() {
		System.out.println("Click on Login");
		loginButtonElement.click();
		return new DashBoardPage();
	}
	
	public boolean isLoginSuccessfullyDisplayed () {
		return isElementDisplayedWithWait(loginSuccessfulElement);
	}
	
	public boolean isLoginUnsuccessfulElementDisplayed () {
		return isElementDisplayedWithWait(loginUnsuccessfulElement);
	}
	
	public boolean isEmailRequiredElementDisplayed() {
		return isElementDisplayed(emailIsRequiredElement);
	}
	
	public boolean isPasswordRequiredElementDisplayed() {
		return isElementDisplayed(passwordIsRequired);
	}
	
	public String getCurrentUrl() {
		return super.getCurrentUrl();
	}
}
