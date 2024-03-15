package com.book.actioninterface;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface ActionInterface {

	void setExplicitWait(WebDriver driver, WebElement element, long timeoutInSeconds);

	void setFluentWait(WebDriver driver, WebElement element, long timeoutInSeconds, long pollingTimeout,
			Class<? extends Throwable> exceptionType);

}
