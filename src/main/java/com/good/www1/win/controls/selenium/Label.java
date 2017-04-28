package com.good.www1.win.controls.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.good.www1.win.pages.selenium.DriverFactory;

public final class Label implements ILabel {
	private WebElement webElement;
	static WebDriver driver = DriverFactory.getFirefox(); // We need it to work
															// with the same
															// driver object

	private Label(WebElement webElement) {
		this.webElement = webElement;
	}

	private static ILabel get(WebElement webElement) {
		return new Label(webElement);
	}

	public static ILabel getById(String id) {
		return get(driver.findElement(By.id(id)));
	}

	public static ILabel getByName(String name) {
		return get(driver.findElement(By.name(name)));
	}

	public static ILabel getByXpath(String xpath) {
		return get(driver.findElement(By.xpath(xpath)));
	}

	public static ILabel getByCssSelector(String cssSelector) {
		return get(driver.findElement(By.xpath(cssSelector)));
	}

	public static ILabel getByTagName(String tagName) {
		return get(driver.findElement(By.xpath(tagName)));
	}

	WebElement getControl() {
		return webElement;
	}

	public boolean isDisplayed() {
		return webElement.isDisplayed();
	}

	public boolean isEnabled() {
		return webElement.isEnabled();
	}

	public String getText() {
		return webElement.getText();
	}

}
