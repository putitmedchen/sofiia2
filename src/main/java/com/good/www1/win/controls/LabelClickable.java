package com.good.www1.win.controls;

import com.good.www1.win.asserts.LoggerWrapper;
import com.good.www1.win.tools.ISikuliPatternWrapper;
import com.good.www1.win.tools.SearchPattern;
import com.good.www1.win.tools.SikuliPatternWrapper;
import org.sikuli.script.Match;

import java.util.Iterator;

public class LabelClickable implements ILabelClickable {
    private ISikuliPatternWrapper control;

    private LabelClickable(ISikuliPatternWrapper control) {
        this.control = control;
    }

    public static ILabelClickable getVisibleElementByRelativePath(String page,
                                                                  String element) {
        return get(SikuliPatternWrapper.getVisibleElementByRelativePath(page,
                element));
    }

    public static ILabelClickable getVisibleElementByRelativePath(String page,
                                                                  String element, int AccordingToCenterOfPatternByX,
                                                                  int AccordingToCenterOfPatternByY) {
        return get(SikuliPatternWrapper.getVisibleElementByRelativePath(page,
                element, AccordingToCenterOfPatternByX,
                AccordingToCenterOfPatternByY));
    }

    public static Boolean getPossiblyVisibleElementByRelativePath(String page,
                                                                          String element) {
        boolean isVisible = true;
        int initialTimeout = SearchPattern.get().getTimeOut();
        SearchPattern.get().setTimeOut(25);

        try {
            get(SikuliPatternWrapper.getVisibleElementByRelativePath(page,
                    element));
                    }
        catch (Exception e){
            isVisible = false;
            LoggerWrapper.get().infoLog("Currently element is NOT visible " + page + "/" + element);
        }
        finally {
            SearchPattern.get().setTimeOut(initialTimeout);
        }

        return isVisible;

    }

    public static void getNotVisibleElementByRelativePath(String page,
                                                          String element) {
        SikuliPatternWrapper.getNotVisibleElementByRelativePath(page,
                element);
    }

    public static ILabelClickable getVisibleElementByAbsolutePath(
            String absolutePath) {
        return get(SikuliPatternWrapper
                .getVisibleElementByAbsolutePath(absolutePath));
    }

    public static ILabelClickable getVisibleElementByAbsolutePath(
            String absolutePath, int AccordingToCenterOfPatternByX,
            int AccordingToCenterOfPatternByY) {
        return get(SikuliPatternWrapper.getVisibleElementByAbsolutePath(
                absolutePath, AccordingToCenterOfPatternByX,
                AccordingToCenterOfPatternByY));
    }

    private static ILabelClickable get(ISikuliPatternWrapper control) {
        return new LabelClickable(control);
    }

    public void click() {
        control.click();
    }

    public void rightclick() {
        control.rightClick();
    }

    public void selectAllText() {
        control.selectAllText();
    }

    public boolean isVisible() {
        return control.isVisible();
    }

    public void moveToElement() {
        control.moveToElement();
    }

    public void checkAllItems() {
        control.clickAllItems();
    }

    public Iterator<Match> findAllItems() {
        return control.findAllItems();
    }

    public void doubleClick() {
        control.doubleClick();
    }


}
