package com.book.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.book.base.TestBase;
import com.book.dataprovider.ReadExcel;
import com.book.pages.GiftCardPage;
import com.book.pages.HomePage;

public class GiftCardTest extends TestBase {

	public WebDriver driver;
	HomePage homePage;
	GiftCardPage giftCardPage;

	public GiftCardTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		driver = initializeBrowser();
		homePage = new HomePage(driver);
		giftCardPage = homePage.navigateToGiftCard();

	}

	@Test(priority = 1)
	public void testGiftCard() throws Exception {

		giftCardPage.scrollMouse();
		giftCardPage.clickGiftCards();

		giftCardPage.giftCardForm(dataProp.getProperty("amount"), dataProp.getProperty("month"),
				dataProp.getProperty("date"));

		String[] recipientDetails = ReadExcel.getData(0).split(" ");
		giftCardPage.giftCardRecipientData(recipientDetails[0], recipientDetails[1], recipientDetails[2]);

		String[] senderDetails = ReadExcel.getData(1).split(" ");
		giftCardPage.giftCardSenderData(senderDetails[0], senderDetails[1], senderDetails[2], senderDetails[3],
				senderDetails[4]);

		String pincodeError = giftCardPage.reCheckPincode();
		System.out.println("Error Pincode: " + pincodeError);

		Thread.sleep(3000);

		String[] reFillData = ReadExcel.getData(2).split(" ");
		giftCardPage.reEnterPincode(reFillData[0]);

		giftCardPage.reCheckRecipientsMob(reFillData[1]);

		giftCardPage.giftCardConfirmBtn(reFillData[2]);

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
