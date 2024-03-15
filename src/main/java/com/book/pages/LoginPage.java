package com.book.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.book.action.ActionDriver;
import com.book.base.TestBase;

public class LoginPage extends TestBase {

	WebDriver driver;
	Actions actions;
	public JavascriptExecutor js;
	ActionDriver actionDriver;

	@FindBy(xpath = "//div[contains(text(),'Login with your email ID')]")
	WebElement loginVerify;

	@FindBy(xpath = "//div[@id='password-credentials']//input[@id='spree_user_email']")
	WebElement loginEmail;

	@FindBy(xpath = "//div[@class='password']/input[@id='spree_user_password' and @class='required input_authentication']")
	WebElement loginPassword;

	@FindBy(xpath = "//input[@id='ul_site_login']")
	WebElement loginBtn;
	
	@FindBy(xpath = "//span[@class='header-icon-link user-profile-icon']")
	WebElement dropDown;
	
	@FindBy(xpath = "//a[@id='header-icon-profile']")
	WebElement profileIcon;
	
	@FindBy(xpath = "//h1[contains(text(),'My Account')]")
	WebElement loginStatusVerify;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		js = (JavascriptExecutor) driver;
		actionDriver = new ActionDriver(driver);
		actions = new Actions(driver);
	}

	public String verifyLoginPage() throws InterruptedException {
		actionDriver.setExplicitWait(driver, loginBtn, 10);
		Thread.sleep(3000);
		return loginVerify.getText();
	}

	public void loginValidation(String email, String password) throws InterruptedException {
		loginEmail.sendKeys(email);
		loginPassword.sendKeys(password);
		loginBtn.click();
	}
	
	public void navigateToProfile() {
		actions.moveToElement(dropDown).build().perform();
		profileIcon.click();
	}
	
	public String loginStatus() {
		return loginStatusVerify.getText();
	}

}
