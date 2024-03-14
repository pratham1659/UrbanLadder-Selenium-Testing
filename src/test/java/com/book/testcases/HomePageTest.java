package com.book.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.book.base.TestBase;
import com.book.pages.HomePage;

public class HomePageTest extends TestBase {

	public WebDriver driver;
	HomePage homePage;

	public HomePageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {

		driver = initializeBrowser();
		homePage = new HomePage(driver);

	}

	@Test(priority = 0)
	public void navigateToHomePage() throws InterruptedException {

		Assert.assertTrue(homePage.verifyLogo());

	}

	@Test(priority = 1, enabled = false)
	public void navigateToLoginPage() throws InterruptedException {
		homePage.clickOnLoginDropdownMenu();

		String verifyLogin = homePage.verifyLoginPage();

		Assert.assertEquals(verifyLogin, "Login with your email ID");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
