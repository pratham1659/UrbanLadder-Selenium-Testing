package com.book.testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.book.base.TestBase;
import com.book.pages.HomePage;
import com.book.pages.LoginPage;

public class HomePageTest extends TestBase {

	public WebDriver driver;
	HomePage homePage;
	LoginPage loginPage;
	public static Logger log;

	public HomePageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {

		driver = initializeBrowser();
		homePage = new HomePage(driver);
		log = LogManager.getLogger(HomePageTest.class);

	}

	@Test(priority = 0)
	public void navigateToHomePage() throws InterruptedException {

		log.info("Start The Test");

		Assert.assertTrue(homePage.verifyLogo());

		log.info("Welcome To Home Page");
		
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
