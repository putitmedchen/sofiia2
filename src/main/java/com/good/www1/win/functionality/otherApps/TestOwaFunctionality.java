package com.good.www1.win.functionality.otherApps;

import com.good.www1.win.data.IEMail;
import com.good.www1.win.data.IUser;
import com.good.www1.win.pages.otherapps.owa.OwaComposeEmailPage;
import com.good.www1.win.pages.otherapps.owa.OwaGoodLoginPage;
import com.good.www1.win.pages.otherapps.owa.OwaMainPage;

public class TestOwaFunctionality {

    private OwaGoodLoginPage owaGoodLoginPage;
    private OwaMainPage owaMainPage;
    private OwaComposeEmailPage owaComposeEmailPage;

    public void loginToOwaGood(IUser user) {
        owaGoodLoginPage = new OwaGoodLoginPage();
        owaGoodLoginPage.loginToOwaGood(user);

        owaMainPage = new OwaMainPage();
        owaMainPage.initElements();
    }

    public void composeEmailWithAttachment(IEMail eMail) {
        owaMainPage = new OwaMainPage();
        owaMainPage.initElements();
        owaMainPage.clickOnNewEmailButton();

        owaComposeEmailPage = new OwaComposeEmailPage();
        owaComposeEmailPage.initElements();
        owaComposeEmailPage.composeMessageWithAttachment(eMail);

    }

    public void openAttachmentFile(IEMail email) {
        owaComposeEmailPage = new OwaComposeEmailPage();
        owaComposeEmailPage.openAttachmentFile(email);
    }

    public void sendEmail(IEMail mail) {
        owaMainPage = new OwaMainPage();
        owaMainPage.initElements();
        pause(9000);
        owaMainPage.clickOnNewEmailButton();

        owaComposeEmailPage = new OwaComposeEmailPage();
        owaComposeEmailPage.initElements();
        owaComposeEmailPage.composeEmail(mail);
    }

    public void checkPerformanceEmailInOwa() {
        owaComposeEmailPage = new OwaComposeEmailPage();
        owaComposeEmailPage.checkPerformanceInboxEmail();
    }

    public void clickOnLogOut() {
        owaComposeEmailPage.clickOnLogOut();
    }

    private void pause(long milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
