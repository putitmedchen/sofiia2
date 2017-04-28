package com.good.www1.win.controls.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.good.www1.win.pages.selenium.DriverFactory;

public final class Button implements IButton {
	private String EMPTY = "";
	private WebElement webElement;
	static WebDriver driver = DriverFactory.getFirefox(); //We need it to work with the same driver object

	private Button(WebElement webElement) {
		this.webElement = webElement;
	}

	private static IButton get(WebElement webElement) {
		return new Button(webElement);
	}

	public static IButton getById(String id) {
		return get(driver.findElement(By.id(id)));
	}

	public static IButton getByName(String name) {
		return get(driver.findElement(By.name(name)));
	}

	public static IButton getByXpath(String xpath) {
		return get(driver.findElement(By.xpath(xpath)));
	}

	public static IButton getByCssSelector(String cssSelector) {
		return get(driver.findElement(By.xpath(cssSelector)));
	}

	public static IButton getByTagName(String tagName) {
		return get(driver.findElement(By.xpath(tagName)));
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

	public void setFocus() {
		webElement.sendKeys(EMPTY);
	}

	public void submit() {
		webElement.submit();
	}

	public void click() {
		webElement.click();
	}
	
	public void rightClick() {
		try {
			Actions action = new Actions(driver).contextClick(webElement);
			action.build().perform();

			System.out.println("Sucessfully Right clicked on the element");
		} catch (StaleElementReferenceException e) {
			System.out.println("Element is not attached to the page document "
					+ e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element " + webElement + " was not found in DOM "
					+ e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Element " + webElement + " was not clickable "
					+ e.getStackTrace());
		}
	}
	
}
