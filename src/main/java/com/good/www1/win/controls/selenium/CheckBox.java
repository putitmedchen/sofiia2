package com.good.www1.win.controls.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.good.www1.win.pages.selenium.DriverFactory;

public final class CheckBox implements ICheckBox {
	
	private WebElement webElement;
	static WebDriver driver = DriverFactory.getFirefox(); //We need it to work with the same driver object

    private CheckBox(WebElement webElement) {
    	this.webElement = webElement;
    }
    
	private static ICheckBox get(WebElement webElement) {
		return new CheckBox(webElement);
	}

    public static ICheckBox getById(String id) {
    	return get(driver.findElement(By.id(id)));
    }

    public static ICheckBox getByName(String name) {
    	return get(driver.findElement(By.name(name)));
    }

    public static ICheckBox getByXpath(String xpath) {
    	return get(driver.findElement(By.xpath(xpath)));
    }

    public static ICheckBox getByCssSelector(String cssSelector) {
    	return get(driver.findElement(By.xpath(cssSelector)));
    }

    public static ICheckBox getByTagName(String tagName) {
    	return get(driver.findElement(By.xpath(tagName)));
    }

    public boolean isChecked() {
        return webElement.isSelected();
    }

    public boolean isDisplayed() {
        return webElement.isDisplayed();
    }

    public boolean isEnabled() {
        return webElement.isEnabled();
    }

    public void submit() {
    	webElement.submit();
    }

    public void checked() {
        if (!isChecked()) {
            click();
        }
    }

    public void unchecked() {
        if (isChecked()) {
            click();
        }
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
