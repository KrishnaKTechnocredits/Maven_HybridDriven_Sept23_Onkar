/*1. complete the TC1 & TC2 from below sheet
https://docs.google.com/spreadsheets/d/1XAsOC5KFoI_AOnB9Zwn4AvSSU0pHXyGgoCexc8Pt-HE/edit#gid=0
*/

package testscripts;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import constants.ConstantPath;
import utility.ExcelOperations;

public class LoginTests extends TestBase{
	
	@Test
	public void verifyLogin(){
		loginPage.login("patilonkar18@gmail.com", "Ishani@1");
		boolean loginFlag = loginPage.isLoginSuccessfullyDisplayed();
		Assert.assertTrue(loginFlag); 
	}
	
	@Test
	public void verifyErrorMassage() {
		System.out.println("STEP- Click on Login Button");
		loginPage.clickOnLoginButton();
		
		System.out.println("VERIFY- Error message for email is visible");
		boolean emailErrorFlag = loginPage.isEmailRequiredElementDisplayed();
		Assert.assertTrue(emailErrorFlag);
		
		System.out.println("VERIFY- Error message for password is visible");
		Assert.assertTrue(loginPage.isPasswordRequiredElementDisplayed());
	}
	
	@Test
	public void verifyPasswordErrorMassage() {
		System.out.println("STEP- Enter valid user email");
		loginPage.enterUserEmail("patilonkar18@gmail.com");
		
		System.out.println("STEP- Click on Login Button");
		loginPage.clickOnLoginButton();
		
		System.out.println("VERIFY- Error message for email is visible");
		boolean emailErrorFlag = loginPage.isEmailRequiredElementDisplayed();
		Assert.assertFalse(emailErrorFlag);
		
		System.out.println("VERIFY- Error message for password is visible");
		Assert.assertTrue(loginPage.isPasswordRequiredElementDisplayed());
	}
	
	@Test
	public void verifyEmailErrorMassage() {
		System.out.println("STEP- Enter valid Password");
		loginPage.enterPassword("Ishani@1");
		
		System.out.println("STEP- Click on Login Button");
		loginPage.clickOnLoginButton();
		
		System.out.println("VERIFY- Error message for email is visible");
		boolean emailErrorFlag = loginPage.isEmailRequiredElementDisplayed();
		Assert.assertTrue(emailErrorFlag);
		
		System.out.println("VERIFY- Error message for password is visible");
		Assert.assertFalse(loginPage.isPasswordRequiredElementDisplayed());
	}
//	
//	@Test(dataProvider = "LoginDataProvider")
//	public void verifyLoginUsingDataProvider(String userName, String password, String loginStatus) {
//		System.out.println("STEP- Login with given credentials");
//		loginPage.login(userName, password);
//		String currentURL = "";
//		
//		boolean loginFlag;
//		if(loginStatus.equalsIgnoreCase("pass")) {
//			System.out.println("VERIFY- Login successful message displayed");
//			loginFlag = loginPage.isLoginSuccessfullyDisplayed();
//			Assert.assertTrue(loginFlag, "Login Successful message is not displayed");
//			
//			System.out.println("VERIFY- Incorrect email or password message is not displayed");
//			loginFlag = loginPage.isLoginUnsuccessfulElementDisplayed();
//			Assert.assertFalse(loginFlag, "Incorrect email or password message is displayed");
//			
//			currentURL = loginPage.getCurrentUrl();
//			System.out.println("VERIFY- Application should redirect to the dashboard page");
//			Assert.assertTrue(currentURL.endsWith("dashboard/dash"), "Current URL :"+currentURL);
//		}else {
//			loginFlag = loginPage.isLoginUnsuccessfulElementDisplayed();
//			Assert.assertTrue(loginFlag, "Incorrect email or password message is not displayed");
//			
//			loginFlag = loginPage.isLoginSuccessfullyDisplayed();
//			Assert.assertFalse(loginFlag, "Login Successful message is displayed");
//			
//			currentURL = loginPage.getCurrentUrl();
//			Assert.assertFalse(loginFlag, "Login Successful message is displayed");
//			Assert.assertTrue(currentURL.endsWith("auth/login"));
//		}
//	}
	
	@DataProvider(name="LoginDataProvider")
	public Object[][] getLoginData() throws IOException{
		return ExcelOperations.getAllRows(ConstantPath.LOGIN_WORKBOOK_PATH, "Login");
	}
}
