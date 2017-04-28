package com.good.www1.win.tests.installation;

import com.good.www1.win.testNG.TestNGListener;
import com.good.www1.win.utils.BuildUtils;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNGListener.class)
public class InstallLatestBuildTest {

    private String lastBuildNumberLocal;
    private BuildUtils buildUtils = new BuildUtils();

    @Test(description = "this test copies build from network directory to local directory in $Project Dir/tools")
    public void installNewBuild() {
        lastBuildNumberLocal = buildUtils.getLastWinBuildNumber();
        buildUtils.runBuild(lastBuildNumberLocal);
    }
}

