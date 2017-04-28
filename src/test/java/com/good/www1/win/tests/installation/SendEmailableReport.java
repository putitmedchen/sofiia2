package com.good.www1.win.tests.installation;

import com.good.www1.win.data.EMailRepository;
import com.good.www1.win.data.IEMail;
import com.good.www1.win.data.IUser;
import com.good.www1.win.data.UserRepository;
import com.good.www1.win.functionality.otherApps.TestOwaFunctionality;
import com.good.www1.win.functionality.applicationSettingsFunctionality.SendReportFunctionality;
import org.testng.annotations.Test;

public class SendEmailableReport {

    private static final IEMail email = EMailRepository.getEmailForSendingReports();
    private static final IUser gmailReportUser = UserRepository.getGmailReportUser();
    private SendReportFunctionality sendReportFunctionality = new SendReportFunctionality();
    private TestOwaFunctionality owaGoodFunctionality = new TestOwaFunctionality();

    @Test
    public void loginToGmail(){
        sendReportFunctionality.loginToGmail(gmailReportUser);
    }

    @Test(dependsOnMethods = "loginToGmail")
    public void sendReport() {
        sendReportFunctionality.composeMessage(email);
        owaGoodFunctionality.openAttachmentFile(email);
        sendReportFunctionality.sendMessage();
    }

}
