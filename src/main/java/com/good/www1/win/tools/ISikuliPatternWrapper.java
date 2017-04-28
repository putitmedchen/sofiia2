package com.good.www1.win.tools;

import org.sikuli.script.Match;

import java.util.Iterator;

public interface ISikuliPatternWrapper {

    void enterKey();

    void click();

    void rightClick();

    void clear();

    void typeText(String text);

    boolean isVisible();

    void selectAllText();

    void moveToElement();

    Iterator<Match> findAllItems();

    void clickAllItems();

    void pasteText(String text);

    void paste();

    void doubleClick();

    void copy();

    void quit();

    void typeKeyDefiniteTimes(String key, int times);

}
