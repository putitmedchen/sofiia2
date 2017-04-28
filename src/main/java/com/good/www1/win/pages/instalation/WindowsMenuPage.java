package com.good.www1.win.pages.instalation;

import com.good.www1.win.controls.ILabelClickable;
import com.good.www1.win.controls.LabelClickable;

public class WindowsMenuPage {

    private ILabelClickable windowsButton;
    private ILabelClickable programsAndFeaturesLink;

    public void initElements(){
        windowsButton = LabelClickable.getVisibleElementByRelativePath("installation/WindowsMenuPage",
                "windowsButton");
    }

    public void openProgramAndFeatures(){
        windowsButton.rightclick();
        programsAndFeaturesLink = LabelClickable.getVisibleElementByRelativePath("installation/WindowsMenuPage",
                "programsAndFeaturesLink");
        programsAndFeaturesLink.click();

    }

}

