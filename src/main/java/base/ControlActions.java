package base;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import constants.ConstantPath;
import utility.PropOperations;

public abstract class ControlActions {

	protected static WebDriver driver;
	private static PropOperations propOperations;
	private static WebDriverWait wait;

	static public void launchBrowser() {
		String browser = System.getProperty("browserName") == null ? "chrome" : System.getProperty("browserName");
		System.out.println("Browser value : " + System.getProperty("browserName"));
		switch (browser) {
		case "chrome":
			propOperations = new PropOperations(ConstantPath.ENV_FILEPATH);
			System.setProperty(ConstantPath.CHROME_DRIVER_KEY, ConstantPath.CHROME_DRIVER_VALUE);
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get(propOperations.getValue("url"));
			wait = new WebDriverWait(driver, ConstantPath.WAIT);
			break;

		case "edge":
			propOperations = new PropOperations(ConstantPath.ENV_FILEPATH);
			System.setProperty(ConstantPath.EDGE_DRIVER_KEY, ConstantPath.EDGE_DRIVER_VALUE);
			driver = new EdgeDriver();
			driver.manage().window().maximize();
			driver.get(propOperations.getValue("url"));
			wait = new WebDriverWait(driver, ConstantPath.WAIT);
			break;

		default:
			throw new RuntimeException("Browser mismiatch");
		}

	}

	public static void closeBrowser() {
		driver.close();
	}

	protected WebElement getElement(String locatorType, String locatorValue, boolean isWaitRequired) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement e = null;

		switch (locatorType.toUpperCase()) {
		case "XPATH":
			if (isWaitRequired)
				e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
			else
				e = driver.findElement(By.xpath(locatorValue));
			break;

		case "CSS":
			if (isWaitRequired)
				e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
			else
				e = driver.findElement(By.cssSelector(locatorValue));
			break;

		case "ID":
			if (isWaitRequired)
				e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
			else
				e = driver.findElement(By.id(locatorValue));
			break;

		case "NAME":
			if (isWaitRequired)
				e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
			else
				e = driver.findElement(By.name(locatorValue));
			break;

		case "LINKTEXT":
			if (isWaitRequired)
				e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
			else
				e = driver.findElement(By.linkText(locatorValue));
			break;

		case "PARTIALLINKTEXT":
			if (isWaitRequired)
				e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(locatorValue)));
			else
				e = driver.findElement(By.partialLinkText(locatorValue));
			break;

		case "CLASSNAME":
			if (isWaitRequired)
				e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locatorValue)));
			else
				e = driver.findElement(By.className(locatorValue));
			break;

		case "TAGNAME":
			if (isWaitRequired)
				e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locatorValue)));
			else
				e = driver.findElement(By.tagName(locatorValue));
			break;

		default:
			System.out.println("Locator is INVALID");
		}
		return e;
	}

	protected void waitForElementToBeVisible(WebElement e) {
		wait.until(ExpectedConditions.visibilityOf(e));
	}

	protected void waitForElementToBeClickable(WebElement e) {
		wait.until(ExpectedConditions.elementToBeClickable(e));
	}

	protected void waitForElementToBeInvisible(WebElement e) {
		WebDriverWait wait = new WebDriverWait(driver, ConstantPath.FAST_WAIT);
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}

		try {
			wait.until(ExpectedConditions.invisibilityOf(e));
		} catch (NoSuchElementException ne) {
			System.out.println(1);
		} catch (TimeoutException te) {
			System.out.println(2);
		}
	}

	protected boolean isElementDisplayed(WebElement e) {
		try {
			return e.isDisplayed();
		} catch (NoSuchElementException ne) {
			return false;
		}
	}

	protected boolean isElementDisplayedWithWait(WebElement e) {
		try {
			wait.until(ExpectedConditions.visibilityOf(e));
			return true;
		} catch (Exception ne) {
			return false;
		}
	}

	protected String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public static void takeScreenshot(String fileName) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile, new File(".//Screenshots/" + fileName + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected List<String> getElementTextList(List<WebElement> listOfWebElement) {
		List<String> listOfElementText = new ArrayList<String>();
		for (WebElement element : listOfWebElement) {
			listOfElementText.add(element.getText());
		}
		return listOfElementText;
	}

	protected void clickOnElement(String locatorType, String locatorValue, boolean isWaitRequired) {
		clickOnElement(locatorType, locatorValue, isWaitRequired, false);
	}

	protected void clickOnElement(String locatorType, String locatorValue, boolean isWaitRequired,
			boolean isWaitRequiredBeforeClick) {
		WebElement e = getElement(locatorType, locatorValue, isWaitRequired);
		if (isWaitRequiredBeforeClick) {
			wait.until(ExpectedConditions.elementToBeClickable(e)).click();
		}
		e.click();
	}

	// Fix this method
	protected void clickOnElement(WebElement element, boolean isWaitRequired) {
		if (isWaitRequired) {
			wait.until(ExpectedConditions.visibilityOf(element));
			element = wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
		} else
			element.click();
	}

	protected String getElementText(String locatorType, String locatorValue, boolean isWaitRequired) {
		return getElement(locatorType, locatorValue, isWaitRequired).getText();
	}

	protected String getElementText(WebElement e, boolean isWaitRequired) {
		if (isWaitRequired)
			waitForElementToBeVisible(e);
		return e.getText();
	}

	protected List<WebElement> waitUntilElementToBeLessThan(int num, String locatorValue) {
		return wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath(locatorValue), num));
	}

}
