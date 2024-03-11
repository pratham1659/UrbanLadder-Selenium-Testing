package com.book.testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SearchTest {

	WebDriver driver;
	String baseUrl = "https://www.urbanladder.com/";

	@BeforeMethod
	public void setUp() throws InterruptedException {
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--remote-allow-origins=*");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(op);
		driver.get(baseUrl);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		String urlString = driver.getCurrentUrl();
		System.out.println(urlString);

	}

	@Test
	public void homePage() throws InterruptedException {

		driver.findElement(By.xpath("//input[@id='search']")).sendKeys("bedroom & mattresses");
		driver.findElement(By.id("search_button")).click();
		
		
		String searchVerify =  driver.findElement(By.xpath("//h2[@class='withsubtext']")).getText();
		System.out.println(searchVerify);
		
		
		Assert.assertEquals(searchVerify, "Search Results For 'Bedroom & Mattresses'");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
