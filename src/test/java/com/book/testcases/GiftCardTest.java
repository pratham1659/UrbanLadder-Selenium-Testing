package com.book.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.book.base.TestBase;
import com.book.pages.GiftCardPage;
import com.book.pages.HomePage;

public class GiftCardTest extends TestBase {

	public WebDriver driver;
	HomePage homePage;
	GiftCardPage giftCardPage;

	public GiftCardTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		driver = initializeBrowser();
		homePage = new HomePage(driver);
		giftCardPage = homePage.navigateToGiftCard();

	}
	
	@Test
	public void testGiftCard() throws Exception {
		
		giftCardPage.scrollMouse();
		giftCardPage.clickGiftCards();
		
		giftCardPage.giftCardForm(dataProp.getProperty("amount"), dataProp.getProperty("month"), dataProp.getProperty("date"));
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
