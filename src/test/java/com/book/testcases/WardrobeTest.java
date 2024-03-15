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

public class WardrobeTest extends TestBase {

	WebDriver driver;
	HomePage homePage;
	WardrobePage wardrobePage;

	public WardrobeTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		driver = initializeBrowser();
		homePage = new HomePage(driver);
		wardrobePage = homePage.navigateToWardrobeSection();
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

	@Test(priority = 0)
	public void warDrobeTest() throws InterruptedException {

		Thread.sleep(5000);
		wardrobePage.switchToPopup();

		wardrobePage.verifyWardrobePage();

		wardrobePage.selectPriceOption();

		wardrobePage.selectBrandOption();

		wardrobePage.selectDoorOption();

		wardrobePage.selectMaterialOption();
		
		List<WebElement> wardrobe = wardrobePage.wardrobeProductNameList();
		
		List<WebElement> prices = wardrobePage.wardrobePriceList();
		
		printProductNameAndPrice(wardrobe, prices, 5);
		
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
