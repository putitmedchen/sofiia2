package com.good.www1.win.pages.instalation;

import com.good.www1.win.controls.ILabelClickable;
import com.good.www1.win.controls.LabelClickable;

public class InstallationDialogPage {

    private ILabelClickable nextButton;
    private ILabelClickable installButton;
    private ILabelClickable finishButton;

    public void clickOnNextButton() {
        nextButton = LabelClickable.getVisibleElementByRelativePath("installation/InstallationDialogPage",
                "nextButton");
        nextButton.click();
    }

    public void clickOnInstallButton() {
        installButton = LabelClickable.getVisibleElementByRelativePath("installation/InstallationDialogPage",
                "installButton");
        installButton.click();
    }

    public void clickOnFinishButton() {
        finishButton = LabelClickable.getVisibleElementByRelativePath("installation/InstallationDialogPage",
                "finishButton");
        finishButton.click();
    }
}
