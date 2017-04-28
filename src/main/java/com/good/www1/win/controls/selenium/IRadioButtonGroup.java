package com.good.www1.win.controls.selenium;

import java.util.List;

public interface IRadioButtonGroup {

	boolean isChecked();

	String getText();

	String getName();

	void selectByPartialText(String partialText);

	int getRadioButtonSize();

	List<IRadioButtonLight> getRadioButtonGroup();

}
