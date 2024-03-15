package com.book.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.book.base.TestBase;
import com.book.pages.HomePage;
import com.book.pages.LoginPage;
import com.book.utils.LoggerUtil;

public class LoginPageTest extends TestBase {

	WebDriver driver;
	HomePage homePage;
	LoginPage loginPage;
	LoggerUtil log;

	@BeforeMethod
	public void setUp() throws InterruptedException {
		driver = initializeBrowser();
		log = new LoggerUtil();
		homePage = new HomePage(driver);
		loginPage = homePage.navigateToLoginPage();

	}

	@Test
	public void testLogin() throws InterruptedException {

		log.info("Navigate to Login Page");
		String verifyLogin = loginPage.verifyLoginPage();
		
		Assert.assertEquals(verifyLogin, "Login with your email ID");
		
		log.info("Verify Login Page");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
