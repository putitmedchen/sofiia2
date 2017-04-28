package com.good.www1.win.testNG;

import com.good.www1.win.functionality.otherApps.selenium.IFireFoxFunctionality;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

@Listeners(TestNGListener.class)
public class FirefoxTestCase {

    protected IFireFoxFunctionality iFireFoxFunctionality;

    @BeforeClass
    public void launchFireFox() {
        iFireFoxFunctionality = new IFireFoxFunctionality();
        iFireFoxFunctionality.closeFireFox();
    }

    @AfterClass(alwaysRun = true)
    public void closeDriver() {
        iFireFoxFunctionality.closeFireFox();
    }

    public void pause(long milisec) {

        try {
            Thread.sleep(milisec);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static String teststationIniFile = "/src/test/resources/testBlackAccess.ini";
    private static String maxBuildFile = "/src/test/resources/maxBuild.ini";


    public static String getArtifactReleaseUrl() {
        return getIniFileValue("provision.blackAccess.artifact", teststationIniFile);
    }

    public static String getBlackWorkArtifact() {
        return getIniFileValue("provision.blackWork.artifact", teststationIniFile);
    }

    public static String getBlackWorkUrl() {
        return getIniFileValue("provision.blackWork.url", teststationIniFile);
    }

    public static String getBlackWorkCrx() {
        return getIniFileValue("provision.blackWork.crx", teststationIniFile);
    }

    public static String getBlackAccessExe() {
        return getIniFileValue("provision.blackAccess.exe", teststationIniFile);
    }

    public static String getBlackAccessUrl() {
        String blackAccessUrl = getIniFileValue("provision.blackAccess.url", teststationIniFile);
        return getIniFileValue("provision.blackAccess.url", teststationIniFile);
    }

    public static String getBulidSavePath() {
        return getAbsolutePath() + getIniFileValue("provision.blackAccess.savePath", teststationIniFile);
    }

    public static String getIniFileValue(String key, String path) {
        Properties prop = new Properties();
        InputStream input = null;
        String value = null;

        try {

            input = new FileInputStream(getAbsolutePath() + path);

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            value = prop.getProperty(key);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }

    private static Path getAbsolutePath() {
        return Paths.get("").toAbsolutePath();
    }

}



