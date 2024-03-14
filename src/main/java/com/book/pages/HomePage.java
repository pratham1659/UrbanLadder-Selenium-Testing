package com.book.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;
	public JavascriptExecutor js;
	Actions actions;

	@FindBy(xpath = "//figure[@class='header__topBar_logo']")
	WebElement urbanLadderLogo;

	@FindBy(xpath = "//li[@class='header__topBarIconList_profile-icon']")
	WebElement accountDropMenu;

	@FindBy(xpath = "//a[@id='header-icon-login']")
	WebElement loginBtn;

	@FindBy(xpath = "//div[contains(text(),'Login with your email ID')]")
	WebElement loginVerify;

	// Objects
	@FindBy(xpath = "//a[@class='category']//h4[contains(text(), 'Wardrobes')]")
	WebElement wardrobeIcon;

	public HomePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
		actions = new Actions(driver);
		js = (JavascriptExecutor) driver;

	}

	public void clickOnLoginDropdownMenu() {
		actions.moveToElement(accountDropMenu).build().perform();
		loginBtn.click();
	}

	public String verifyLoginPage() {
		return loginVerify.getText();

	}

	public boolean verifyLogo() {
		return urbanLadderLogo.isDisplayed();
	}

	public SearchPage navigateToSearchPage() {
		return new SearchPage(driver);
	}

	public WardrobePage navigateToWardrobeSection() {
		js.executeScript("window.scrollBy(0,700)");
		wardrobeIcon.click();
		return new WardrobePage(driver);
	}

}
