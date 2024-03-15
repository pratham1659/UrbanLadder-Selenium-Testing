package com.book.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.book.action.ActionDriver;
import com.book.base.TestBase;
import com.book.pages.HomePage;
import com.book.pages.LoginPage;
import com.book.utils.LoggerUtil;

public class LoginPageTest extends TestBase {

	WebDriver driver;
	HomePage homePage;
	LoginPage loginPage;
	LoggerUtil log;
	ActionDriver actionDriver;

	@BeforeMethod
	public void setUp() throws InterruptedException {
		driver = initializeBrowser();
		log = new LoggerUtil();
		homePage = new HomePage(driver);
		actionDriver = new ActionDriver(driver);
		loginPage = homePage.navigateToLoginPage();

	}

	@Test(priority = 0)
	public void testLogin() throws InterruptedException {

		log.info("Navigate to Login Page");
		String verifyLogin = loginPage.verifyLoginPage();

		Assert.assertEquals(verifyLogin, "LOGIN WITH YOUR EMAIL ID");
		log.info("Verify Login Page Successfully");
		
		loginPage.loginValidation(dataProp.getProperty("email"), dataProp.getProperty("password"));
		log.info("Login Successfully");
		
		loginPage.navigateToProfile();
		log.info("Navigate to Profile Page");
		
		String verifyLoginStatus =  loginPage.loginStatus();
		Assert.assertEquals(verifyLoginStatus, "My Account");
		log.info("Login Status Verfied");
	}


	@AfterMethod
	public void tearDown() {
//		driver.quit();
	}

}
