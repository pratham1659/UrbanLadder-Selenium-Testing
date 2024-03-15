package com.book.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.book.base.TestBase;

public class LoginPage extends TestBase {
	
	WebDriver driver;
	Actions actions;
	public JavascriptExecutor js;
	
	@FindBy(xpath = "//div[contains(text(),'Login with your email ID')]")
	WebElement loginVerify;


	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		js = (JavascriptExecutor) driver;
		actions = new Actions(driver);
	}
	
	public String verifyLoginPage() {
		return loginVerify.getText();
	}

}
