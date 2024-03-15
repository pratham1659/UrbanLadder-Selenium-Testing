package com.book.testcases;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.book.base.TestBase;
import com.book.pages.HomePage;
import com.book.pages.WardrobePage;
import com.book.utils.LoggerUtil;
import com.book.utils.TestUtils;

public class WardrobeTest extends TestBase {

	WebDriver driver;
	HomePage homePage;
	WardrobePage wardrobePage;
	LoggerUtil log;

	public WardrobeTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		driver = initializeBrowser();
		log = new LoggerUtil();
		homePage = new HomePage(driver);
		wardrobePage = homePage.navigateToWardrobeSection();
	}

	@Test(priority = 0)
	public void wardrobeTest() throws InterruptedException {

		log.info("WarDrobe Test Started");
		
		wardrobePage.switchToPopup();
		log.info("Close the Popup window");

		wardrobePage.verifyWardrobePage();
		log.info("Wardrobe Page Verify Successfully");

		wardrobePage.selectPriceOption();
		log.info("Set the Price Filter");

		wardrobePage.selectBrandOption();
		log.info("Set the Brand Filter");

		wardrobePage.selectDoorOption();
		log.info("Set the Door Filter");

		wardrobePage.selectMaterialOption();
		log.info("Set the Material Filter");
		
		List<WebElement> wardrobeProducts = wardrobePage.wardrobeProductNameList();
		
		List<WebElement> prices = wardrobePage.wardrobePriceList();
		
		log.info("Wardrobe Filter Results Printed Here: ");
		log.info("Total products: " + wardrobeProducts.size());
		TestUtils.printProductNameAndPrice(wardrobeProducts, prices, 5);
		
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
