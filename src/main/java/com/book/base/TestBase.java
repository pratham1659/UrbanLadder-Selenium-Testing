package com.book.base;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	
	WebDriver driver;
	public Properties prop;
	public Properties dataProp;
	
	public WebDriver initializeBrowser() {

		String browserName = "chrome";
		String myUrl = "https://www.urbanladder.com/";
		

		if (browserName.equalsIgnoreCase("chrome")) {
			ChromeOptions op = new ChromeOptions();
			op.addArguments("--remote-allow-origins=*");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(op);

		} else if (browserName.equalsIgnoreCase("firefox")) {
			FirefoxOptions op = new FirefoxOptions();
			op.addArguments("--remote-allow-origins=*");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver(op);

		} else if (browserName.equalsIgnoreCase("edge")) {
			EdgeOptions op = new EdgeOptions();
			op.addArguments("--remote-allow-origins=*");
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver(op);

		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		driver.get(myUrl);
		return driver;
	}

}
