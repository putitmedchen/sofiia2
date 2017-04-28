package com.good.www1.win.controls;

import org.sikuli.script.Match;

import java.util.Iterator;

public interface ILabelClickable extends ILabel {

    void click();

    void rightclick();
    //boolean isVisible();

    void selectAllText();

    void moveToElement();

    void checkAllItems();

    Iterator<Match> findAllItems();

    void doubleClick();
}
