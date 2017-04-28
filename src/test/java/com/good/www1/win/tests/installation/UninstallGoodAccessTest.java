package com.good.www1.win.tests.installation;

import com.good.www1.win.functionality.browser.OpenBrowserWindowFunctionality;
import com.good.www1.win.testNG.TestNGListener;
import com.good.www1.win.utils.BuildUtils;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNGListener.class)
public class UninstallGoodAccessTest {

    private OpenBrowserWindowFunctionality openBrowserWindowFunctionality = new OpenBrowserWindowFunctionality();
    private BuildUtils buildUtils = new BuildUtils();

    @Test
    public void killGoodProcess() {
        openBrowserWindowFunctionality.killGoodProcess();
    }

    @Test(dependsOnMethods = "killGoodProcess")
    public void uninstall() {
        buildUtils.uninstallBuild();
    }

}
