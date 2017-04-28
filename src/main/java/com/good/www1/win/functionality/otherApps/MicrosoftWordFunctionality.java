package com.good.www1.win.functionality.otherApps;

import com.good.www1.win.pages.otherapps.word.WordBlankDocumentPage;
import com.good.www1.win.pages.otherapps.word.WordHomePage;

public class MicrosoftWordFunctionality {

    private WordHomePage wordHomePage;
    private WordBlankDocumentPage wordBlankDocumentPage;

    public void launchWord() {
        wordHomePage = new WordHomePage();
        wordHomePage.launchWord();
    }

    public void copyTextFromWord(String text) {
        wordHomePage = new WordHomePage();
        wordHomePage.openBlankWordDocument();

        wordBlankDocumentPage = new WordBlankDocumentPage();
        wordBlankDocumentPage.typeText(text);
        wordBlankDocumentPage.copyText();
    }

    public void pasteToWord() {
        wordHomePage = new WordHomePage();
        wordHomePage.openBlankWordDocument();
        wordBlankDocumentPage = new WordBlankDocumentPage();
        wordBlankDocumentPage.pasteButtonClick();
    }

    public void checkTextAfterPaste() {
        wordBlankDocumentPage.checkAutomationTextAfterPaste();
    }

    public void closeWord() {
        wordBlankDocumentPage = new WordBlankDocumentPage();
        wordBlankDocumentPage.closeWord();
    }

    public void killWord() {
        wordHomePage = new WordHomePage();
        wordHomePage.killWord();
    }

}
