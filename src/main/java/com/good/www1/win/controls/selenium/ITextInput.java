package com.good.www1.win.controls.selenium;

public interface ITextInput extends IClickable {

	boolean isDisplayed();

	boolean isEnabled();

	String getText();

	void sendKeys(String text);

	void submit();

	void clear();

}
