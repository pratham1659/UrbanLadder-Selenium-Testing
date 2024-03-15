package com.book.testcases;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.book.base.TestBase;
import com.book.pages.HomePage;
import com.book.pages.SearchPage;
import com.book.utils.LoggerUtil;

public class SearchTest extends TestBase {

	WebDriver driver;
	HomePage homePage;
	SearchPage searchPage;
	LoggerUtil log;

	@BeforeMethod
	public void setUp() {
		driver = initializeBrowser();
		log = new LoggerUtil();
		homePage = new HomePage(driver);
		searchPage = homePage.navigateToSearchPage();	
	}

	public void printProductNameAndPrice(List<WebElement> productSizeList, List<WebElement> productPriceList,
			int size) {
		for (int i = 0; i < size; i++) {
			String productName = productSizeList.get(i).getText();
			String productPrice = productPriceList.get(i).getText();
			System.out.println("Product-Name: " + productName);
			System.out.println("Product-Price: " + productPrice);
		}
	}

	@Test
	public void navigateToSearchTest() throws InterruptedException {

		log.info("Start the Search Test");
	
		searchPage.searchAndFind("wardrobe");
		log.info("Searching... wardrobe in Search Input");

		String searchVerify = searchPage.verifySearchPage();
		log.info("Search page Verified Successfully");

		searchPage.scrollToViewProducts();
		log.info("Scroll to View Products Successfully");

		searchPage.switchToPopup();
		log.info("Close the popup");

		searchPage.selectContactOption();
		log.info("Search Categroy Filter");

		searchPage.selectPriceOption();
		log.info("Search Price Filter");

		log.info("Search Results Fetch from Site");
		Assert.assertEquals(searchVerify, "Search Results For 'Wardrobe'");

		List<WebElement> wardrobeProducts = searchPage.wardrobeProductNameList();

		List<WebElement> prices = searchPage.wardrobePriceList();
		
		log.info("Total number of products: " + wardrobeProducts.size());
		printProductNameAndPrice(wardrobeProducts, prices, wardrobeProducts.size());
		log.info("Test completed successfully");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
		log.info("End The Search Test");
	}
}
