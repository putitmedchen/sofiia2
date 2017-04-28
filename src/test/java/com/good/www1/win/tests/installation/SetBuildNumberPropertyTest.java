package com.good.www1.win.tests.installation;

import com.good.www1.win.testNG.TestNGListener;
import com.good.www1.win.utils.BuildUtils;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNGListener.class)
public class SetBuildNumberPropertyTest {

    private String lastBuildNumber;

    private BuildUtils buildUtils = new BuildUtils();

    @Test(description = "this test creates html message subject which is send in report")
    public void createReportMessageSubject() {
        lastBuildNumber = buildUtils.getBuildNumberFromCmd();
        buildUtils.createReportMessageSubject(lastBuildNumber);
    }

    @Test(dependsOnMethods = "createReportMessageSubject", description = "this test creates html message body which sends in report ")
    public void createReportMessageBody() {
        lastBuildNumber = buildUtils.getBuildNumberFromCmd();
       // buildUtils.setMavenProperty(lastBuildNumber);
        buildUtils.createReportMessageBody(lastBuildNumber);
    }

}
