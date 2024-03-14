package com.book.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

	WebDriver driver;

	@FindBy(xpath = "//input[@id='search']")
	WebElement searchBar;

	@FindBy(xpath = "//button[@id='search_button']")
	WebElement searchBtn;
	
	@FindBy(xpath = "//h2[@class='withsubtext']")
	WebElement searchVerify;

	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void searchAndFind(String item) {
		searchBar.sendKeys(item);
		searchBtn.click();
	}

	public String verifySearchPage() {
		return searchVerify.getText();
	}
}
