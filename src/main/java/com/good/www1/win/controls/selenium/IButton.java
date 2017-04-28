package com.good.www1.win.controls.selenium;

public interface IButton extends IClickable {

    boolean isDisplayed();

    boolean isEnabled();

    String getText();

    void setFocus();

    void submit();
    
}
