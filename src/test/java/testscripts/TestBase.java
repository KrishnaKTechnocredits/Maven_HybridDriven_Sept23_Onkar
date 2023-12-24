package testscripts;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import base.ControlActions;
import pages.DashBoardPage;
import pages.LoginPage;

public class TestBase {

	LoginPage loginPage;
	DashBoardPage dashboardPage;
	static int count=1;

	@BeforeMethod
	void setup() {
		ControlActions.launchBrowser();
		loginPage = new LoginPage();
		dashboardPage = new DashBoardPage();
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		if (ITestResult.FAILURE == result.getStatus())
			ControlActions.takeScreenshot(result.getName()+"_"+count++);
		ControlActions.closeBrowser();
	}
	
	void login() {
		loginPage.login("patilonkar18@gmail.com", "Ishani@1");
		Assert.assertTrue(loginPage.isLoginSuccessfullyDisplayed());
	}
}
