package com.book.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.book.base.TestBase;
import com.book.pages.HomePage;
import com.book.pages.SearchPage;

public class SearchTest extends TestBase{

	WebDriver driver;
	HomePage homePage;
	SearchPage searchPage;

	@BeforeMethod
	public void setUp() {
		driver = initializeBrowser();
		homePage = new HomePage(driver);
		searchPage = homePage.navigateToSearchPage();
	}

	@Test
	public void homePage() throws InterruptedException {

		searchPage.searchAndFind("wardrobe");
		
		String searchVerify =  searchPage.verifySearchPage();
		System.out.println(searchVerify);
	
		Assert.assertEquals(searchVerify, "Search Results For 'Wardrobe'");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
