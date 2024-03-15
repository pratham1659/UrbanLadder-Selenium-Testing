package com.book.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class GiftCardPage {

	WebDriver driver;
	Actions actions;
	public JavascriptExecutor js;

	@FindBy(xpath = "//h3[contains(text(),'Weddings')]")
	WebElement WeddingCardClick;
	
	@FindBy(id = "ip_2251506436")
	WebElement amountFeild;
	
	@FindBy(xpath = "//div[@class='_3PNvG']/select[1]")
	WebElement monthFeild;
	
	@FindBy(xpath = "//div[@class='_3PNvG']/select[2]")
	WebElement dateFeild;
	
	@FindBy(xpath = "//button[contains(text(),'Next')]")
	WebElement nextBtn;
	
	

	public GiftCardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		actions = new Actions(driver);
		js = (JavascriptExecutor) driver;

	}

	public void scrollMouse() {
		js.executeScript("window.scrollBy(0,500)");
	}

	public void clickGiftCards() {
		actions.moveToElement(WeddingCardClick).build().perform();
		WeddingCardClick.click();
	}
	
	public void giftCardForm(String amt, String mth, String dt) throws InterruptedException {
		amountFeild.sendKeys(amt);
		
		Select month = new Select(monthFeild);
		month.selectByValue(mth);
		
		Select dateSelect = new Select(dateFeild);
		dateSelect.selectByVisibleText(dt);
		
		Thread.sleep(2000);
		nextBtn.click();
		
		
	}

}
