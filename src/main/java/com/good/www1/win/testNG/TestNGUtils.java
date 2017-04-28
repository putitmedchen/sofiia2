package com.good.www1.win.testNG;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.sikuli.script.ScreenImage;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;

import com.good.www1.win.tools.CaptureScreenImage;
import com.good.www1.win.tools.SikuliScreenWrapper;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

/**
 * Created by sgol on 6/3/15.
 */
public class TestNGUtils {

    private String goodAccessPass = getGoodAccessPass();
    private String currentDay = getCurrentDataByFormat("yyy_MM_dd");
    private String currentMinutes = getCurrentDataByFormat("HH_mm");
    private String packageName;
    private String testName;
    private String testDir;
    private static final String Right_Slash = "/";

    protected void createGoodAccessDir() {
        createDir(goodAccessPass);
    }

    protected void createCurrentDayDir() {
        createDir(goodAccessPass + Right_Slash + currentDay);
    }

    protected String createTestDir(ITestResult result) {

        testName = getTestName(result);

        testDir = goodAccessPass + Right_Slash + currentDay + Right_Slash + testName + " " + currentMinutes;

        createDir(testDir);

        return testDir + Right_Slash;

    }

    protected void createDir(String path) {
        File dir = new File(path);

        if (!dir.exists()) {
            Assert.assertTrue(dir.mkdir(), "directory with pass: " + path + " isn't created");
        }
    }

    protected String takeScreenshot(String testFolderPath, String screenShotName) {
        String screenshotPath = testFolderPath + screenShotName + ".png";
        try {
            File srcFile = new File(CaptureScreenImage.get().captureAndSaveScreen());
            FileUtils.copyFile(srcFile, new File(screenshotPath));
        } catch (Exception e) {
            // e.printStackTrace();

            // TODO Develop My Exception
            throw new RuntimeException(e);
        } return screenshotPath;
    }


    private String getCurrentDataByFormat(String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date();

        return dateFormat.format(date);
    }

    protected String getTestName(ITestResult result) {
        result.getTestClass();
        String[] testNames = result.getTestClass().getName().split("[.]");
        testName =  testNames[testNames.length - 1];

        return testName;
    }

    protected String getPackageName(ITestResult result){
        result.getTestClass();
        String[] testNames = result.getTestClass().getName().split("[.]");
        packageName = testNames[testNames.length - 2] ;

        return packageName;
    }

    protected String getTestNameFromContext(ITestContext context) {
        String[] testNames = context.getCurrentXmlTest().getClasses().toString().split("[.]");
        packageName = testNames[testNames.length - 2];
        testName = testNames[testNames.length - 1].replace("]", "");

        return packageName + "." + testName;
    }

    protected String getMethodName(ITestResult result) {
        return result.getMethod().getMethodName();
    }

    private String getGoodAccessPass() {

        String goodAccessPath = null;
        String osName = System.getProperty("os.name");

        if (osName.startsWith("Mac")) {
            goodAccessPath = "/Users/" + System.getProperty("user.name") + "/GoodAccess";
        } else if (osName.startsWith("Windows")) {
            goodAccessPath = "C:\\Users\\" + System.getProperty("user.name") + "\\GoodAccess";
        }
        return goodAccessPath;
    }

}
