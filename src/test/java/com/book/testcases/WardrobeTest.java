package com.book.testcases;

import org.openqa.selenium.WebDriver;
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
	
	@Test
	public void warDrobeTest() throws InterruptedException {
		
		Thread.sleep(5000);
		wardrobePage.switchToPopup();
		
		wardrobePage.verifyWardrobePage();
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
