package com.book.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.book.action.ActionDriver;
import com.book.base.TestBase;

public class WardrobePage extends TestBase {

	WebDriver driver;
	public JavascriptExecutor js;
	Actions actions;
	ActionDriver actionDriver;

	@FindBy(xpath = "//h1[contains(text(),'Wardrobes')]")
	WebElement verifyWardrobePage;

	@FindBy(partialLinkText = "Close")
	WebElement closePopup;

	@FindBy(xpath = "//div[contains(text(), 'Price')]")
	WebElement priceOption;

	@FindBy(xpath = "//div[@class='noUi-handle noUi-handle-upper']")
	WebElement slider;

	@FindBy(xpath = "//div[@class='gname' and contains(text(),'Brand')]")
	WebElement BrandOption;

	@FindBy(xpath = "//input[@id='filters_brand_name_By_Trevi']")
	WebElement selectBrandFilter;

	@FindBy(xpath = "//div[@class='gname' and contains(text(),'No of Doors')]")
	WebElement NoOfDoorOption;

	@FindBy(xpath = "//input[@id='filters_num_doors_2']")
	WebElement selectDoorFilter;

	@FindBy(xpath = "//div[@class='gname' and contains(text(),'Material')]")
	WebElement materialOption;

	@FindBy(xpath = "//input[@id='filters_material_Engineered_Wood']")
	WebElement selectMaterialOption;

	@FindBy(xpath = "//*[@class='product-title product-title-sofa-mattresses']/span")
	WebElement wardrobeList;

	public WardrobePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		actions = new Actions(driver);
		actionDriver = new ActionDriver(driver);
		js = (JavascriptExecutor) driver;
	}

	public void switchToPopup() throws InterruptedException {
		Thread.sleep(2000);
		closePopup.click();
	}

	public String verifyWardrobePage() {
		return verifyWardrobePage.getText();
	}

	public void selectPriceOption() throws InterruptedException {
		actions.moveToElement(priceOption).build().perform();
		Thread.sleep(2000);
		actions.dragAndDropBy(slider, -280, 0).perform();

	}

	public void selectBrandOption() throws InterruptedException {
		actions.moveToElement(BrandOption).build().perform();
		Thread.sleep(2000);
		selectBrandFilter.click();
	}
	
	public void selectDoorOption() throws InterruptedException {
		Thread.sleep(2000);
		actions.moveToElement(NoOfDoorOption).build().perform();
		selectDoorFilter.click();
	}

	public void selectMaterialOption() {
		actionDriver.setExplicitWait(driver, materialOption, 20);
		actions.moveToElement(materialOption).build().perform();
		selectMaterialOption.click();
	}

	public void scrollToViewProducts() {
		js.executeScript("window.scrollBy(0,700)");
		WebElement navbar = driver.findElement(By.id("topnav_wrapper"));
		js.executeScript("arguments[0].scrollIntoView();", navbar);
	}

	public List<WebElement> wardrobeProductNameList() {
		return driver.findElements(By.xpath("//*[@class='product-title product-title-sofa-mattresses']/span"));
	}

	public List<WebElement> wardrobePriceList() {
		return driver.findElements(By.xpath("//*[@class='price-number']/span"));
	}

}
