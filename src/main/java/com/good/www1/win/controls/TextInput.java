package com.good.www1.win.controls;

import com.good.www1.win.tools.ISikuliPatternWrapper;
import com.good.www1.win.tools.SikuliPatternWrapper;

public class TextInput implements ITextInput {

    private ISikuliPatternWrapper control;

    private TextInput(ISikuliPatternWrapper control) {
        this.control = control;
    }

    public static ITextInput getVisibleElementByRelativePath(String page,
                                                             String element) {
        return get(SikuliPatternWrapper.getVisibleElementByRelativePath(page,
                element));
    }

    public static void getNotVisibleElementByRelativePath(String page,
                                                          String element) {
        SikuliPatternWrapper.getNotVisibleElementByRelativePath(page,
                element);
    }

    public static ITextInput getVisibleElementByRelativePath(String page,
                                                             String element, int AccordingToCenterOfPatternByX,
                                                             int AccordingToCenterOfPatternByY) {
        return get(SikuliPatternWrapper.getVisibleElementByRelativePath(page,
                element, AccordingToCenterOfPatternByX,
                AccordingToCenterOfPatternByY));
    }

    public static ITextInput getVisibleElementByAbsolutePath(String absolutePath) {
        return get(SikuliPatternWrapper
                .getVisibleElementByAbsolutePath(absolutePath));
    }

    public static ITextInput getVisibleElementByAbsolutePath(
            String absolutePath, int AccordingToCenterOfPatternByX,
            int AccordingToCenterOfPatternByY) {
        return get(SikuliPatternWrapper.getVisibleElementByAbsolutePath(
                absolutePath, AccordingToCenterOfPatternByX,
                AccordingToCenterOfPatternByY));
    }

    private static ITextInput get(ISikuliPatternWrapper control) {
        return new TextInput(control);
    }

    public void enterKey() {
        control.enterKey();
    }

    public void click() {
        control.click();
    }

    public void clear() {
        control.clear();
    }

    public void typeText(String text) {
        control.typeText(text);
    }

    public boolean isVisible() {
        return control.isVisible();
    }

    @Override
    public void moveToElement() {
        control.moveToElement();
    }

    public void selectAllText() {
        control.selectAllText();
    }

    public void pasteText(String text) {
        control.pasteText(text);
    }

    public void paste() {
        control.paste();
    }

    public void copy() {
        control.copy();
    }

    @Override
    public void quit() {
        control.quit();
    }

    @Override
    public void typeKeyDefiniteTimes(String key, int times) {
        control.typeKeyDefiniteTimes(key, times);
    }

}
