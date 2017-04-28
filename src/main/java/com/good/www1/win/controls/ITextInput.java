package com.good.www1.win.controls;

public interface ITextInput extends ILabel {

    void enterKey();

    void click();

    void clear();

    void typeText(String text);

    void selectAllText();

    void pasteText(String url);

    void paste();

    void copy();

    void quit();

    void typeKeyDefiniteTimes(String backspace, int times);

    //boolean isVisible();

}
