package tests;

import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.BaseTest;
import drivers.DriverProvider;
import screens.ProductsScreen;

public class ProductsTest extends BaseTest {

	@BeforeClass
	public void startServer() throws MalformedURLException {
		driver = DriverProvider.createAppiumDriver("Android", "emulator-5556");
	}
	@Test(priority=1)
	public void validateListedProducts() {
		List<String> pdts = Arrays.asList("Sauce Labs Backpack","Sauce Labs Bike Light","Sauce Labs Bolt T-Shirt","Sauce Labs Fleece Jacket","Sauce Labs Onesie","Test.allTheThings() T-Shirt");
		Assert.assertTrue(new ProductsScreen(driver).validateProducts(pdts),"Some products are missing");
	}
	
	@Test(priority=2)
	public void validatePricingSort() {
		new ProductsScreen(driver).clickSortButton();
		new ProductsScreen(driver).selectSort("Price","Ascending");
		new ProductsScreen(driver).validateTypeSortedInExpectedOrder("price", "asc");
	}
	
	@Test(priority=3)
	public void validateItemsSort() {
		new ProductsScreen(driver).clickSortButton();
		new ProductsScreen(driver).selectSort("Name","Descending");
		new ProductsScreen(driver).validateTypeSortedInExpectedOrder("price", "desc");
	}
}