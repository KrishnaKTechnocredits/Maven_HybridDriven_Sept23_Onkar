package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.ControlActions;

public class CartPage extends ControlActions{

	@FindBy(xpath = "//div[@class='cart']/ul")
	List<WebElement> listOfProducts;
	
	@FindBy(xpath = "//button[@class='btn btn-danger']")
	List<WebElement> listOfDeleteButton;

	@FindBy(xpath = "//h1[text()='No Products in Your Cart !']")
	WebElement noProductInCart;
	
	@FindBy(xpath ="//button[contains(text(),'Cart')]/label")
	WebElement cartMenuProductCount;
	
	@FindBy(xpath = "//div[contains(text(),'Product Added To Cart')]")
	WebElement cartAddedSuccessMessage;
	
	public CartPage() {
		PageFactory.initElements(driver, this);
	}
	
	public int getTotalNumberOfProductsInMyCart() {
		return listOfProducts.size();
	}
	
	public List<String> getListOfProductsNameInMyCart(){
		return getElementTextList(listOfProducts);
	}
	
	public boolean isProductAvailableInMyCart(String productName) {
		return getElementTextList(listOfProducts).contains(productName);
	}
	
	public boolean isProductDisplayedAtTheEndOfList(String productName) {
		int lastProductIndex = getElementTextList(listOfProducts).size()-1;
		if (getElementTextList(listOfProducts).get(lastProductIndex).equals(productName))
			return true;
		else
			return false;
	}
	
	public String getProductPrice(String productName) {
		String locator = String.format("//h3[text()='%s']/../following-sibling::div[@class='prodTotal cartSection']/p", productName);
		return getElementText("XPATH", locator, false);
	}
	
	public void clickOnRemoveProductButton(String productName) {
		String locator = String.format("//h3[text()='%s']/following::div[contains(@class,'removeWrap')]/button[2]", productName);
		clickOnElement("XPATH", locator, true);
	}
	
	public void removeAllElementsFromcart() {
		for(WebElement e : listOfDeleteButton)
			e.click();
	}
	
	public boolean isNoProductInCartDisplayed() {
		return isElementDisplayed(noProductInCart);
	}
	
	public void clickOnBuyNowButton(String productName) {
		String locator = String.format("//h3[text()='%s']/following::button[@class='btn btn-primary'][1]", productName);
		clickOnElement("XPATH", locator, true);
	}
	
	public int getProductCountOnCartMenu() {
		return Integer.parseInt(getElementText(cartMenuProductCount, true));
	}
	
	public List<String> getProductDetail(String productName){
		List<String> productDetailsList = new ArrayList<String>();
		String locator = String.format("//h3[text()='%s']/preceding-sibling::p", productName);
		productDetailsList.add(getElementText("XPATH", locator, true));
		
		locator = String.format("//h3[text()='%s']/following-sibling::p[1]", productName);
		productDetailsList.add(getElementText("XPATH", locator, false).trim());
		
		locator = String.format("//h3[text()='%s']/following-sibling::p[2]", productName);
		productDetailsList.add(getElementText("XPATH", locator, false).trim());
		
		productDetailsList.add(getProductPrice(productName));
		
		return productDetailsList;
	}
}
