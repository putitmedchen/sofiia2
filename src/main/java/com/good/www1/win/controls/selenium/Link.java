package com.good.www1.win.controls.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.good.www1.win.pages.selenium.DriverFactory;

public final class Link implements ILink {
	
	private WebElement webElement;
	static WebDriver driver = DriverFactory.getFirefox(); // We need it to work
															// with the same driver object

    private Link(WebElement webElement) {
        this.webElement = webElement;
    }
    
	private static ILink get(WebElement webElement) {
		return new Link(webElement);
	}

    public static ILink getById(String id) {
    	return get(driver.findElement(By.id(id)));
    }

    public static ILink getByName(String name) {
    	return get(driver.findElement(By.name(name)));
    }

    public static ILink getByXpath(String xpath) {
    	return get(driver.findElement(By.xpath(xpath)));
    }

    public static ILink getByPartialLinkText(String partialLinkText) {
    	return get(driver.findElement(By.partialLinkText(partialLinkText)));
    }

    public static ILink getByCssSelector(String cssSelector) {
    	return get(driver.findElement(By.cssSelector(cssSelector)));
    }

    public static ILink getByTagName(String tagName) {
    	return get(driver.findElement(By.tagName(tagName)));
    }

 
    public boolean isDisplayed() {
        return webElement.isDisplayed();
    }

    public String getText() {
        return webElement.getText();
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
