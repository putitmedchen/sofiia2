package com.good.www1.win.controls;

import com.good.www1.win.tools.ISikuliPatternWrapper;
import com.good.www1.win.tools.SikuliPatternWrapper;

public class Select implements ISelect {

    private ISikuliPatternWrapper control;

    private Select(ISikuliPatternWrapper control) {
        this.control = control;
    }

    public static ISelect getVisibleElementByRelativePath(String page,
            String element) {
        return get(SikuliPatternWrapper.getVisibleElementByRelativePath(page,
                element));
    }

    public static ISelect getVisibleElementByRelativePath(String page,
            String element, int AccordingToCenterOfPatternByX,
            int AccordingToCenterOfPatternByY) {
        return get(SikuliPatternWrapper.getVisibleElementByRelativePath(page,
                element, AccordingToCenterOfPatternByX,
                AccordingToCenterOfPatternByY));
    }

    public static ISelect getVisibleElementByAbsolutePath(String absolutePath) {
        return get(SikuliPatternWrapper
                .getVisibleElementByAbsolutePath(absolutePath));
    }

    public static ISelect getVisibleElementByAbsolutePath(String absolutePath,
            int AccordingToCenterOfPatternByX, int AccordingToCenterOfPatternByY) {
        return get(SikuliPatternWrapper.getVisibleElementByAbsolutePath(
                absolutePath, AccordingToCenterOfPatternByX,
                AccordingToCenterOfPatternByY));
    }

    private static ISelect get(ISikuliPatternWrapper control) {
        return new Select(control);
    }

    public void click() {
        control.click();
    }

    public void SelectByText(String text) {
        click();
        // TODO Using text + "\n"
        control.typeText(text);
        control.enterKey();
    }

    public boolean isVisible() {
        return control.isVisible();
    }

    @Override
    public void moveToElement() {
        control.moveToElement();
    }

}
