package com.good.www1.win.functionality;

import com.good.www1.win.pages.instalation.InstallationDialogPage;
import com.good.www1.win.pages.instalation.ProgramAndFeaturesWindowPage;
import com.good.www1.win.pages.instalation.WindowsMenuPage;

public class InstalationFunctionality {

    private WindowsMenuPage windowsMenuPage;
    private ProgramAndFeaturesWindowPage programAndFeaturesWindowPage;
    private InstallationDialogPage installationDialogPage;

    public void openProgramAndFeatures() {
        windowsMenuPage = new WindowsMenuPage();
        windowsMenuPage.initElements();
        windowsMenuPage.openProgramAndFeatures();
    }

    public void uninstallGoodAccess() {
        programAndFeaturesWindowPage = new ProgramAndFeaturesWindowPage();
        programAndFeaturesWindowPage.initElements();
        programAndFeaturesWindowPage.deleteGoodAccess();
        programAndFeaturesWindowPage.closeWindow();
    }

    public void proceedInstallationInterface() {
        installationDialogPage = new InstallationDialogPage();
        installationDialogPage.clickOnNextButton();
        installationDialogPage.clickOnInstallButton();
        installationDialogPage.clickOnFinishButton();
    }

}
