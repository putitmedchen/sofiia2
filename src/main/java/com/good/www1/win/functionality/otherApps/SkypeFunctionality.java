package com.good.www1.win.functionality.otherApps;

import com.good.www1.win.data.IUser;
import com.good.www1.win.pages.otherapps.skype.SkypePage;

public class SkypeFunctionality {

    private SkypePage skypePage;

    public void launchSkype() {
        skypePage = new SkypePage();
        skypePage.launchSkype();
    }

    public void loginToSkype(IUser skypeUser) {
        skypePage = new SkypePage();
        skypePage.loginToSkype(skypeUser);
    }

    public void copyText(String text) {
        skypePage = new SkypePage();
        skypePage.copyText(text);

    }

    public void pasteText() {
        skypePage = new SkypePage();
        skypePage.pasteText();
    }


    public void signOut() {
        skypePage = new SkypePage();
        skypePage.signOut();
    }

    public void checkTextIsPasted() {
        skypePage = new SkypePage();
        skypePage.checkTextIsPasted();
    }

    public void checkTextIsNotPasted() {
        skypePage = new SkypePage();
        skypePage.checkTextIsNotPasted();
    }

    public void killSkype() {
        skypePage = new SkypePage();
        skypePage.killSkype();
    }
}


