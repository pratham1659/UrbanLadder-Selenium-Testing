package com.book.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.book.base.TestBase;
import com.book.dataprovider.ReadExcel;
import com.book.pages.GiftCardPage;
import com.book.pages.HomePage;
import com.book.utils.LoggerUtil;

public class GiftCardTest extends TestBase {

	public WebDriver driver;
	HomePage homePage;
	GiftCardPage giftCardPage;
	LoggerUtil log;

	public GiftCardTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		driver = initializeBrowser();
		log = new LoggerUtil();
		homePage = new HomePage(driver);
		giftCardPage = homePage.navigateToGiftCard();

	}

	@Test(priority = 1)
	public void testGiftCard() throws Exception {

		log.info("Navigate to GiftCard Page");
		giftCardPage.scrollMouse();
		giftCardPage.clickGiftCards();
		log.info("Click on Wedding GiftCard");

		giftCardPage.giftCardForm(dataProp.getProperty("amount"), dataProp.getProperty("month"),
				dataProp.getProperty("date"));
		log.info("Filled GiftCard form Succesfully");

		String[] recipientDetails = ReadExcel.getData(0).split(" ");
		giftCardPage.giftCardRecipientData(recipientDetails[0], recipientDetails[1], recipientDetails[2]);
		log.info("Filled Recipient Details");

		String[] senderDetails = ReadExcel.getData(1).split(" ");
		giftCardPage.giftCardSenderData(senderDetails[0], senderDetails[1], senderDetails[2], senderDetails[3],
				senderDetails[4]);
		log.info("Filled Sender Details");

		String pincodeError = giftCardPage.reCheckPincode();
		log.info("Error Pincode: " + pincodeError);
		
		Assert.assertTrue(giftCardPage.giftCardBtn());
		
		giftCardPage.giftCardConfirmBtn(pincodeError);

	}

	@Test(priority = 2)
	public void giftCardHandlingError() throws Exception {

		log.info("Navigate to GiftCard Page");
		giftCardPage.scrollMouse();
		giftCardPage.clickGiftCards();
		log.info("Click on Wedding GiftCard");

		giftCardPage.giftCardForm(dataProp.getProperty("amount"), dataProp.getProperty("month"),
				dataProp.getProperty("date"));
		log.info("Filled GiftCard form Succesfully");

		String[] recipientDetails = ReadExcel.getData(0).split(" ");
		giftCardPage.giftCardRecipientData(recipientDetails[0], recipientDetails[1], recipientDetails[2]);
		log.info("Filled Recipient Details");

		String[] senderDetails = ReadExcel.getData(1).split(" ");
		giftCardPage.giftCardSenderData(senderDetails[0], senderDetails[1], senderDetails[2], senderDetails[3],
				senderDetails[4]);
		log.info("Filled Sender Details");

		String pincodeError = giftCardPage.reCheckPincode();
		log.info("Error Pincode: " + pincodeError);

		Thread.sleep(3000);

		String[] reFillData = ReadExcel.getData(2).split(" ");
		giftCardPage.reEnterPincode(reFillData[0]);
		log.info("Handling Pincode Error in Sender form");

		giftCardPage.reCheckRecipientsMob(reFillData[1]);
		log.info("Handling Recipent Mobile Error in form");

		giftCardPage.giftCardConfirmBtn(reFillData[2]);
		log.info("Gift Card Sent Successfully");

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
