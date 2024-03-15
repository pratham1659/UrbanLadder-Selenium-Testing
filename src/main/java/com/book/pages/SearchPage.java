package com.book.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

	WebDriver driver;
	Actions actions;
	public JavascriptExecutor js;

	@FindBy(xpath = "//input[@id='search']")
	WebElement searchBar;

	@FindBy(xpath = "//button[@id='search_button']")
	WebElement searchBtn;
	
	@FindBy(xpath = "//h2[@class='withsubtext']")
	WebElement searchVerify;
	
	@FindBy(partialLinkText = "Close")
	WebElement closePopup;
	
	@FindBy(xpath = "//div[@class='gname' and contains(text(), 'Category')]")
	WebElement ContactOption;
	
	@FindBy(xpath = "//input[@id='filters_primary_category_Cupboards']")
	WebElement selectContactOption;
	
	@FindBy(xpath = "//div[contains(text(), 'Price')]")
	WebElement priceOption;
	
	@FindBy(xpath = "//div[@class='noUi-handle noUi-handle-upper']")
	WebElement slider;
	

	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		js = (JavascriptExecutor) driver;
		actions = new Actions(driver);
	}

	public void searchAndFind(String item) {
		searchBar.sendKeys(item);
		searchBtn.click();
	}

	public String verifySearchPage() {
		return searchVerify.getText();
	}
	
	public void scrollToViewProducts() {
		js.executeScript("window.scrollBy(0,400)");
	}
	
	public void switchToPopup() {
		closePopup.click();
	}
	
	public void selectContactOption() throws InterruptedException {
		actions.moveToElement(ContactOption).build().perform();
		Thread.sleep(2000);
		selectContactOption.click();
	}
	
	public void selectPriceOption() throws InterruptedException {
		actions.moveToElement(priceOption).build().perform();
		Thread.sleep(3000);
		actions.dragAndDropBy(slider, -280, 0).perform();

	}
	
	public List<WebElement> wardrobeProductNameList() {
		return driver.findElements(By.xpath("//*[@class='product-title product-title-sofa-mattresses']/span"));
	}
	
	public List<WebElement> wardrobePriceList(){
		return driver.findElements(By.xpath("//*[@class='price-number']/span"));
	}
	
	
	
}
