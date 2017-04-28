package com.good.www1.win.controls;

import com.good.www1.win.tools.ISikuliPatternWrapper;
import com.good.www1.win.tools.SikuliPatternWrapper;

public class Label implements ILabel {

    private ISikuliPatternWrapper control;

    private Label(ISikuliPatternWrapper control) {
        this.control = control;
    }

    public static ILabel getVisibleElementByRelativePath(String page,
            String element) {
        return get(SikuliPatternWrapper.getVisibleElementByRelativePath(page,
                element));
    }

    public static ILabel getVisibleElementByRelativePath(String page,
            String element, int AccordingToCenterOfPatternByX,
            int AccordingToCenterOfPatternByY) {
        return get(SikuliPatternWrapper.getVisibleElementByRelativePath(page,
                element, AccordingToCenterOfPatternByX,
                AccordingToCenterOfPatternByY));
    }

    public static ILabel getVisibleElementByAbsolutePath(String absolutePath) {
        return get(SikuliPatternWrapper
                .getVisibleElementByAbsolutePath(absolutePath));
    }

    public static ILabel getVisibleElementByAbsolutePath(String absolutePath,
            int AccordingToCenterOfPatternByX, int AccordingToCenterOfPatternByY) {
        return get(SikuliPatternWrapper.getVisibleElementByAbsolutePath(
                absolutePath, AccordingToCenterOfPatternByX,
                AccordingToCenterOfPatternByY));
    }

    private static ILabel get(ISikuliPatternWrapper control) {
        return new Label(control);
    }

    public boolean isVisible() {
        return control.isVisible();
    }

    public void moveToElement(){
        control.moveToElement();
    }

}
