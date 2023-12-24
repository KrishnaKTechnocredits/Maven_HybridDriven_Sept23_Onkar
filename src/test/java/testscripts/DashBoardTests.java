package testscripts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DashBoardTests extends TestBase{
	
	@Test
	public void varifyCategoriesItemList() {
		List<String> expectedCategoriesList = new ArrayList<String>(Arrays.asList("fashion","electronics","household"));
		List<String> expectedSubCategoriesList = new ArrayList<String>(Arrays.asList("t-shirts","shirts","shoes","mobiles","laptops"));
		List<String> expectedSearchForList = new ArrayList<String>(Arrays.asList("men","women"));
		
		login();
		System.out.println("STEP - Get total number of items in categories List");
		int actualCategoriesItemSize = dashboardPage.getTotalNumberOfItemsInCategories();
		
		System.out.println("VERIFY - Number of items visible in categories");
		Assert.assertEquals(actualCategoriesItemSize, 3);
		System.out.println("VERIFY - Items visible in Categories");
		List<String> actuaCategoriesList = dashboardPage.getCategoriesItemTextList();
		Assert.assertEquals(expectedCategoriesList, actuaCategoriesList);
		
		System.out.println("STEP - Get total number of items in sub categories List");
		int actualSubCategoriesItemSize = dashboardPage.getTotalNumberOfItemsInSubCategories();
		
		System.out.println("VERIFY - Number of items visible in sub categories");
		Assert.assertEquals(actualSubCategoriesItemSize, 5);
		System.out.println("VERIFY - Items visible in Sub Categories");
		List<String> actuaSubCategoriesList = dashboardPage.getSubCategoriesItemTextList();
		Assert.assertEquals(expectedSubCategoriesList, actuaSubCategoriesList);
		
		System.out.println("STEP - Get total number of items in search for List");
		int actualSearchForItemSize = dashboardPage.getTotalNumberOfItemsInSearchFor();
		
		System.out.println("VERIFY - Number of items visible in search for");
		Assert.assertEquals(actualSearchForItemSize, 2);
		System.out.println("VERIFY - Items visible in Search For");
		List<String> actuaSearchForList = dashboardPage.getSearchForItemTextList();
		Assert.assertEquals(expectedSearchForList, actuaSearchForList);
	}
	
	@Test
	public void verifyFilterTest() {
		login();
		System.out.println("STEP - User selects the electronics checkbox under categories");
		dashboardPage.selectOptionItem("electronics");
		System.out.println("VERIFY - Given option item is selected");
		Assert.assertTrue(dashboardPage.isOptionItemSelected("electronics"));
		
		System.out.println("VERIFY - Options are visible as per applied filter");
		int totalCarts = dashboardPage.getNumberOfElementsAvailaleInCart();
		Assert.assertEquals(totalCarts, 1);		
	}
}
