package com.book.action;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.book.actioninterface.ActionInterface;
import com.book.base.TestBase;

public class ActionDriver extends TestBase implements ActionInterface{

	public WebDriver driver;


	public ActionDriver(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Override
	public void setExplicitWait(WebDriver driver, WebElement element, long timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	@Override
	public void setFluentWait(WebDriver driver, WebElement element, long timeoutInSeconds, long pollingTimeout,
			Class<? extends Throwable> exceptionType) {
		FluentWait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeoutInSeconds))
				.pollingEvery(Duration.ofSeconds(pollingTimeout)).ignoring(exceptionType);

		wait.until(ExpectedConditions.visibilityOf(element));
	}

}
