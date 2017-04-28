package com.good.www1.win.controls.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.good.www1.win.pages.selenium.DriverFactory;

public final class TextInput implements ITextInput {
	private WebElement webElement;
	static WebDriver driver = DriverFactory.getFirefox(); // We need it to work
															// with the same driver object

	private TextInput(WebElement webElement) {
		this.webElement = webElement;
	}
	
	private static ITextInput get(WebElement webElement) {
		return new TextInput(webElement);
	}

	public static ITextInput getById(String id) {
		return get(driver.findElement(By.id(id)));
	}

	public static ITextInput getByName(String name) {
		return get(driver.findElement(By.name(name)));
	}

	public static ITextInput getByXpath(String xpath) {
		return get(driver.findElement(By.xpath(xpath)));
	}

	public static ITextInput getByCssSelector(String cssSelector) {
		return get(driver.findElement(By.cssSelector(cssSelector)));
	}

	public static ITextInput getByTagName(String tagName) {
		return get(driver.findElement(By.tagName(tagName)));
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

	public void sendKeys(String text) {
		if (text != null) {
			click();
			clear();
			webElement.sendKeys(text);
		}
	}

	public void submit() {
		webElement.submit();
	}

	public void clear() {
		webElement.clear();
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
