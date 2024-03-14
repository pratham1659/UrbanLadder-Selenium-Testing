package com.book.pages;

import org.openqa.selenium.Alert;
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
	Alert alert;

	@FindBy(xpath = "//figure[@class='header__topBar_logo']")
	WebElement urbanLadderLogo;

	@FindBy(xpath = "//input[@id='search']")
	WebElement searchBar;

	@FindBy(xpath = "//button[@id='search_button']")
	WebElement searchBtn;

	@FindBy(xpath = "//li[@class='header__topBarIconList_profile-icon']")
	WebElement accountDropMenu;

	@FindBy(xpath = "//a[@id='header-icon-login']")
	WebElement loginBtn;

	@FindBy(xpath = "//div[contains(text(),'Login with your email ID')]")
	WebElement loginVerify;

	// Objects
	@FindBy(xpath = "//a[@class='category']//h4[contains(text(), 'Bookshelves')]")
	WebElement bookshelveIcon;

	@FindBy(xpath = "//h1[contains(text(),'Bookshelves')]")
	WebElement verifyBookshelvePage;

	@FindBy(xpath = "//a[contains(text(),'Close')]")
	WebElement closePopup;

	@FindBy(xpath = "//div[contains(text(), 'Price')]")
	WebElement priceOption;

	@FindBy(xpath = "//div[@class='noUi-handle noUi-handle-upper']")
	WebElement slider;

	@FindBy(xpath = "//div[contains(text(), 'Storage Type')]")
	WebElement storageOption;

	@FindBy(xpath = "//input[@id='filters_storage_type_Open']")
	WebElement selectOpenFilter;

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

	public void clickOnBookShelve() throws InterruptedException {
		js.executeScript("window.scrollBy(0,700)");
		bookshelveIcon.click();
		Thread.sleep(2000);
	}

	public void switchToAlert() {
		alert = driver.switchTo().alert();
		alert.dismiss();

	}

	public String verifyBookshelvePage() {
		return verifyBookshelvePage.getText();

	}

	public void selectPriceOption() throws InterruptedException {
		actions.moveToElement(priceOption).build().perform();
		Thread.sleep(3000);
		actions.dragAndDropBy(slider, -280, 0).perform();

	}

	public void selectStorageOption() {
		storageOption.click();
		selectOpenFilter.click();
	}
}
