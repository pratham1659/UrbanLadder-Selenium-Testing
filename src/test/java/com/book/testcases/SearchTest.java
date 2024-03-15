package com.book.testcases;

import java.util.List;

import org.apache.logging.log4j.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.book.base.TestBase;
import com.book.pages.HomePage;
import com.book.pages.SearchPage;
import com.book.report.ReportGenerator;

public class SearchTest extends TestBase {

	WebDriver driver;
	HomePage homePage;
	SearchPage searchPage;
	public static Logger log;
	public ReportGenerator report;

	@BeforeMethod
	public void setUp() {
		driver = initializeBrowser();
		homePage = new HomePage(driver);
		searchPage = homePage.navigateToSearchPage();
		report = new ReportGenerator();
		log = LogManager.getLogger(SearchTest.class);
		log.info("Logged into Website");
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
	public void homePage() throws InterruptedException {

		log.info("Start the Test");
		report.testName("Start from HomePage");

		searchPage.searchAndFind("wardrobe");

		String searchVerify = searchPage.verifySearchPage();

		searchPage.scrollToViewProducts();

		searchPage.switchToPopup();

		searchPage.selectContactOption();

		searchPage.selectPriceOption();

		log.info("Search Results Fetch from Site");
		report.testName("Search Results Fetch from Site");
		Assert.assertEquals(searchVerify, "Search Results For 'Wardrobe'");

		List<WebElement> wardrobe = searchPage.wardrobeProductNameList();

//		log.error("Exception occured", new Exception ("Element Not Found"));

		List<WebElement> prices = searchPage.wardrobePriceList();

		printProductNameAndPrice(wardrobe, prices, 3);
		report.testName("Test completed successfully");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
		log.info("Test Stop");
		report.testName("Test Stop");
	}
}
