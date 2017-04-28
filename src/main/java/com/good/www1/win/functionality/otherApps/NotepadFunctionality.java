package com.good.www1.win.functionality.otherApps;

import com.good.www1.win.pages.otherapps.notepad.NotepadPage;

public class NotepadFunctionality {

    private NotepadPage notepadPage;

    public void launchNotepad() {
        notepadPage = new NotepadPage();
        notepadPage.autorunNotepad();
    }

    public void copyTextFromNotepad(String text) {
        notepadPage = new NotepadPage();
        notepadPage.copyTextFromNotepad(text);
    }

    public void pasteToNotepad() {
        notepadPage = new NotepadPage();
        notepadPage.pasteToNotepad();
    }

    public void endNotepad() {
        notepadPage = new NotepadPage();
        notepadPage.endNotepad();
    }

    public void checkTextIsPasted() {
        notepadPage = new NotepadPage();
        notepadPage.checkTextIsPasted();
    }

    public void checkTextIsNotPasted() {
        notepadPage = new NotepadPage();
        notepadPage.checkTextIsNotPasted();
    }

    public void dontSaveClick() {
        notepadPage = new NotepadPage();
        notepadPage.dontSaveClick();
    }

    public void killNotepad() {
        notepadPage = new NotepadPage();
        notepadPage.killNotepad();
    }
}


