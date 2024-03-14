package com.book.pages;

import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.book.base.TestBase;

public class WardrobePage extends TestBase{
	
	WebDriver driver;
	public JavascriptExecutor js;
	Actions actions;
	
	@FindBy(xpath = "//h1[contains(text(),'Wardrobes')]")
	WebElement verifyWardrobePage;

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
	
	public WardrobePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void switchToPopup() throws InterruptedException {
		Set<String> windowHandles = driver.getWindowHandles();
		System.out.println("Number of opened windows: " + windowHandles.size());

		for (String str : windowHandles) {
			driver.switchTo().window(str);
			closePopup.click();

		}
	}
	
	public String verifyWardrobePage() {
		return verifyWardrobePage.getText();

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
