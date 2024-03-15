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

	@FindBy(xpath = "//input[@id='ip_4036288348']")
	WebElement recipientName;

	@FindBy(xpath = "//input[@id='ip_137656023']")
	WebElement recipientEmail;

	@FindBy(xpath = "//input[@id='ip_3177473671']")
	WebElement recipientMob;

	@FindBy(xpath = "//input[@id='ip_1082986083']")
	WebElement nameField;

	@FindBy(xpath = "//input[@id='ip_4081352456']")
	WebElement emailField;

	@FindBy(xpath = "//input[@id='ip_2121573464']")
	WebElement mobField;

	@FindBy(xpath = "//input[@id='ip_2194351474']")
	WebElement addressField;

	@FindBy(xpath = "//input[@id='ip_567727260']")
	WebElement pincodeField;
	
	@FindBy(xpath = "//div[@class='_1HVuH']")
	WebElement recheckPin;
	
	@FindBy(xpath = "//input[@id='ip_1554905400']")
	WebElement cityField;

	@FindBy(xpath = "//textarea[@id='ip_582840596']")
	WebElement textAreaField;

	@FindBy(xpath = "//button[contains(text(),'Confirm')]")
	WebElement confirmButton;

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

	public void giftCardRecipientData(String rName, String rEmail, String rMob) {
		recipientName.sendKeys(rName);
		recipientEmail.sendKeys(rEmail);
		recipientMob.sendKeys(rMob);
	}

	public void giftCardSenderData(String fName, String fEmail, String fMob, String adrs, String pin) {
		nameField.sendKeys(fName);
		emailField.sendKeys(fEmail);
		mobField.sendKeys(fMob);
		addressField.sendKeys(adrs);
		pincodeField.sendKeys(pin);
	}
	
	public String reCheckPincode() {
		return recheckPin.getText();
		
	}
	
	public void reCheckRecipientsMob(String rMob) throws InterruptedException {
		Thread.sleep(3000);
		recipientMob.clear();
		Thread.sleep(3000);
		recipientMob.sendKeys(rMob);
	}
	
	public void reEnterPincode(String pin) throws InterruptedException {
		Thread.sleep(3000);
		pincodeField.clear();
		Thread.sleep(3000);
		pincodeField.sendKeys(pin);
	}
	
	public boolean giftCardBtn() {
		return confirmButton.isEnabled();
	}
	
	public void giftCardConfirmBtn(String message) {
		textAreaField.sendKeys(message);
		confirmButton.click();
	}

}
