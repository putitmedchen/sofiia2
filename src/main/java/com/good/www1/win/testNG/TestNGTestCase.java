package com.good.www1.win.testNG;

import com.good.www1.win.data.IUser;
import com.good.www1.win.data.UserRepository;
import com.good.www1.win.functionality.LaunchGoodAccessFunctionality;
import com.good.www1.win.functionality.ChangeTimeOutsFunctionality;
import com.good.www1.win.functionality.browser.BrowserControlsFunctionality;
import com.good.www1.win.functionality.browser.OpenBrowserWindowFunctionality;
import com.good.www1.win.functionality.otherApps.SkypeFunctionality;
import com.good.www1.win.functionality.otherApps.selenium.IChromeFunctionality;
import com.good.www1.win.tools.SearchPattern;
import org.testng.annotations.*;

@Listeners(TestNGListener.class)
public class TestNGTestCase {

    private OpenBrowserWindowFunctionality openBrowserWindowFunctionality = new OpenBrowserWindowFunctionality();
    private LaunchGoodAccessFunctionality launchGoodAccessFunctionality = new LaunchGoodAccessFunctionality();
    private BrowserControlsFunctionality browserControlsFunctionality = new BrowserControlsFunctionality();
    private SkypeFunctionality skypeFunctionality = new SkypeFunctionality();
    private IChromeFunctionality iChromeFunctionality;
    private ChangeTimeOutsFunctionality changeTimeOutsFunctionality = new ChangeTimeOutsFunctionality();

    private static IUser user = UserRepository.getValidUser();
    private int initialTimeout;

    @BeforeClass
    public void before() {
        openBrowserWindowFunctionality.killGoodProcess();
        //onopenBrowserWindowFunctionality.closeCrashDialog();
        launchGoodAccessFunctionality.checkStartAutentication();
        launchGoodAccessFunctionality.authorizeOnGoodAccess(user);
    }

    @AfterClass(alwaysRun = true)
    public void after() {
        openBrowserWindowFunctionality.closeWindow();
        openBrowserWindowFunctionality.killGoodProcess();
        //openBrowserWindowFunctionality.closeCrashDialog();
        //changeTimeOutsFunctionality.setDefaultSimilarity();
    }

    @AfterClass(alwaysRun = true)
    public void killProcess() {
        openBrowserWindowFunctionality.killGoodProcess();
    }

    @BeforeGroups("slow-loading")
    public void increaseLoadTimeout() {
        initialTimeout = SearchPattern.get().getTimeOut();
        SearchPattern.get().setTimeOut(200000);
    }

    @AfterGroups("slow-loading")
    public void decreaseLoadTimeout() {
        SearchPattern.get().setTimeOut(initialTimeout);
    }

    @BeforeGroups("skype")
    public void quitExistedSkype() {
        skypeFunctionality.killSkype();
    }

    @AfterGroups("skype")
    public void quitSkype() {
        skypeFunctionality.killSkype();
    }

    @BeforeGroups("web apps mail")
    public void launchWebAppsMail() {
        browserControlsFunctionality.launchWebApps();
    }

    @BeforeGroups("web apps contacts")
    public void launchContacts() {
        browserControlsFunctionality.launchContacts();
    }

    @BeforeGroups("web apps calendar")
    public void launchCalendar() {
        browserControlsFunctionality.launchCalendar();
    }

    @BeforeGroups("chrome")
    public void launchChrome() {
        iChromeFunctionality = new IChromeFunctionality();
        iChromeFunctionality.closeChrome();
    }

    @AfterGroups(value = "chrome", alwaysRun = true)
    public void closeChrome() {
        iChromeFunctionality.closeChrome();
    }


    protected void pause(long milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
