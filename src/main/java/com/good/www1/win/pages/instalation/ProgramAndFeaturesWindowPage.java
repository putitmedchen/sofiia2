package com.good.www1.win.pages.instalation;

import com.good.www1.win.controls.ILabelClickable;
import com.good.www1.win.controls.LabelClickable;

public class ProgramAndFeaturesWindowPage {

    private ILabelClickable goodAccessIcon;
    private ILabelClickable uninstallButton;
    private ILabelClickable closeButton;


    public void initElements(){
        goodAccessIcon = LabelClickable.getVisibleElementByRelativePath("installation/ProgramAndFeaturesWindow",
                "goodAccessIcon");
        closeButton = LabelClickable.getVisibleElementByRelativePath("installation/ProgramAndFeaturesWindow",
                "closeButton");
    }

    public void deleteGoodAccess(){
        goodAccessIcon.click();
        uninstallButton = LabelClickable.getVisibleElementByRelativePath("installation/ProgramAndFeaturesWindow",
                "uninstallButton");
        uninstallButton.click();

    }

    public void closeWindow() {
        closeButton.click();
    }
}
