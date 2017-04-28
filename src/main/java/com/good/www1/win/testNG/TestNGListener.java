package com.good.www1.win.testNG;

import com.good.www1.win.asserts.LoggerWrapper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.util.regex.Matcher;


/**
 * Created by sgol on 6/3/15.
 */
public class TestNGListener extends TestNGUtils implements ITestListener {

    private final static String FAILURE = "failure ";
    private final static String SUCCESS = "success ";
    private final static String SKIPPED = "skipped ";
    private static LoggerWrapper logger = LoggerWrapper.get();

    private String testFolderPath;

    @Override
    public void onTestStart(ITestResult result) {
        createGoodAccessDir();
        createCurrentDayDir();
        testFolderPath = createTestDir(result);

        logger.log("\r\n" + "\r\n" +
                "      STARTING TEST METHOD: +++++++++++++  " + getTestName(result) + "." + getMethodName(result) + "()" + "\r\n" +
                "      IN PACKAGE:  ++++++++++++++  " + getPackageName(result));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Reporter.setCurrentTestResult(result);

        takeScreenshot(testFolderPath, SUCCESS + getMethodName(result));
        String screenshotPath = takeScreenshot(testFolderPath, FAILURE + getMethodName(result));
        String newPath = screenshotPath.replaceAll("/", Matcher.quoteReplacement(System.getProperty("file.separator")));

        Reporter.log("<a href=\"" + newPath + "\"> Clickhere </a> <img src=\"" + newPath + "\" hight='200' width='200'/> </a>);");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Reporter.setCurrentTestResult(result);

        String screenshotPath = takeScreenshot(testFolderPath, FAILURE + getMethodName(result));
        String newPath = screenshotPath.replaceAll("/", Matcher.quoteReplacement(System.getProperty("file.separator")));

        Reporter.log("<a href=\"" + newPath + "\"> Clickhere </a> <img src=\"" + newPath + "\" hight='200' width='200'/> </a>);");

        takeScreenshot(FAILURE, result.getName());
        Reporter.setCurrentTestResult(null);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        takeScreenshot(testFolderPath, SKIPPED + getMethodName(result));

        String screenshotPath = takeScreenshot(testFolderPath, FAILURE + getMethodName(result));
        String newPath = screenshotPath.replaceAll("/", Matcher.quoteReplacement(System.getProperty("file.separator")));

        Reporter.setCurrentTestResult(result);

        Reporter.log("<a href=\"" + newPath + "\"> Clickhere </a> <img src=\"" + newPath + "\" hight='200' width='200'/> </a>);");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {
        logger.log("\r\n" + "\r\n" + "[INFO] ================================================= STARTING TEST CASE " +
                getTestNameFromContext(context));
    }

    @Override
    public void onFinish(ITestContext context) {

    }

}
