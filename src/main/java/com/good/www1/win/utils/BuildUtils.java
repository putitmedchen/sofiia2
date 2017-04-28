package com.good.www1.win.utils;

import com.good.www1.win.asserts.LoggerWrapper;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BuildUtils {

    private static final String reportMessagePath = "test-output/reportMessage.html";
    private static final String reportMessageSubjectPath = "test-output/reportMessageSubjct.html";

    private static final String buildsFolderPath = System.getProperty("user.dir") + "/tools/";
    private String mavenPropertyCommand = "mvn exec:java -Dbuild.num=";

    private DownloadBBContent download;
    private String blackberryAccessVersion = null;
    private String blackberryWorkVersion = null;

    public File getBuildByNumber(String mappedDrivePath, String lastBuildNumber) {
        File buildsDrive = new File(mappedDrivePath);
        File currentBuild = new File(buildsDrive, lastBuildNumber);

        return currentBuild;
    }

    public String getCurrentBuildNumberLocal(String mappedDrivePath) {
        String lastBuildNumber = null;
        File buildsDrive = new File(mappedDrivePath);
        ArrayList<Integer> buildsNames = new ArrayList<Integer>();
        for (String s : buildsDrive.list()) {
            buildsNames.add(Integer.parseInt(s));
        }
        Collections.sort(buildsNames, Collections.reverseOrder());
        lastBuildNumber = buildsNames.get(0).toString();

        return lastBuildNumber;
    }

    public String getLastWinBuildNumber() {

        String lastBuildNumber = null;
        String lastBuildName = null;
        File buildsDrive = new File(buildsFolderPath);

        ArrayList<String> buildsNames = new ArrayList<String>();
        ArrayList<Integer> buildsNumber = new ArrayList<Integer>();

        for (String s : buildsDrive.list()) {
            Pattern pattern = Pattern.compile("[0-9]{3}");
            Matcher matcher = pattern.matcher(s);

            while (matcher.find()) {
                if (!matcher.group().isEmpty()) {
                    buildsNames.add(s);
                    String number = matcher.group();
                    buildsNumber.add(Integer.parseInt(number));
                    break;
                }
            }
        }
        Collections.sort(buildsNumber, Collections.reverseOrder());
        lastBuildNumber = buildsNumber.get(0).toString();

        for (String ss : buildsNames) {
            if (ss.contains(lastBuildNumber)) {
                lastBuildName = ss;
            }
        }
        return lastBuildName;
    }


    public String getBuildsDestinationPath() {
        String buildsDestinationPath = null;
        String osName = System.getProperty("os.name");

        if (osName.startsWith("Mac")) {
            buildsDestinationPath = "/Users/" + System.getProperty("user.name") + "/GoodAccess/Builds/GoodAccess_Mac/";
        } else if (osName.startsWith("Windows")) {
            buildsDestinationPath = "C:\\Users\\" + System.getProperty("user.name") + "\\GoodAccess\\Builds\\GoodAccess_Wins\\";
        }

        return buildsDestinationPath;
    }

    public void copyBuild(File sourceFolder, File destinationFolder) {
        try {
            FileUtils.copyDirectory(sourceFolder, destinationFolder);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void runBuild(String buildName) {
        File installationBuildDir = new File(buildsFolderPath.toString() + "/");
        String[] installCommand = new String[]{"cmd.exe", "/c", buildName, "/s", "/v\"", "/qb\""};

        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.directory(installationBuildDir);

        processBuilder.command(installCommand);
        try {
            Process process1 = processBuilder.start();
            process1.waitFor();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void createReportMessageBody(String lastBuildNumber) {
        File htmlMessageFile = new File(reportMessagePath);
        htmlMessageFile.setWritable(true);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(htmlMessageFile));
            bw.write("<html><head><title>New Page</title></head><body><p><p>Hi,</p>\n" +
                    "<p>Check out the attached report on build: " + lastBuildNumber + "</p>  Additional info about test results can be found on next page: https://wikis.rim.net/display/goodGMAGD/Automation+test+run+results+for+Blackberry+work </body></html>");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createReportMessageSubject(String lastBuildNumber) {
        File htmlMessageFile = new File(reportMessageSubjectPath);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(htmlMessageFile));
            bw.write("<html><head><title>New Page</title></head><body><p>Report after automation run on Good Access # " + lastBuildNumber + " for Windows");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void uninstallBuild() {
        // String [] uninstallCommand = new String[]{"cmd.exe" ,"/elevate", "/c", uninstallerName, "/S", "/F"};
        String[] uninstallCommand = new String[]{"wmic", "product", "where", "\"name",
                "like", "'%Blackberry%'\"", "call", "uninstall"};

        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command(uninstallCommand);

        try {
            Process process = processBuilder.start();
            process.waitFor();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public String getBuildNumberFromCmd() {
        String[] buildNumberCmdQuery = {"wmic", "product", "where", "\"name",
                "like", "'%Blackberry%'\"", "get", "Version"};
        String buildNumber = null;

        Runtime runtime = Runtime.getRuntime();
        try {
            Process process = runtime.exec(buildNumberCmdQuery);
            InputStream input = process.getInputStream();
            Scanner scanner = new Scanner(input);

            while (scanner.hasNext()) {
                buildNumber = scanner.next();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buildNumber;
    }

    public void setMavenProperty(String lastBuildNumber) {
        mavenPropertyCommand = mavenPropertyCommand + lastBuildNumber;
        Runtime runtime = Runtime.getRuntime();

        try {
            runtime.exec(mavenPropertyCommand);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void pinGAToTaskBar() {
        String pathToGA = "C:\\Users\\" + System.getProperty("user.name") + "\\AppData\\Local\\BlackBerry\\BlackBerry Access\\blackberryaccess.exe";
        String[] pinToTaskBarCommand = new String[]{"cmd.exe", "/c", "tools\\syspin.exe", pathToGA, "c:5386"};

        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command(pinToTaskBarCommand);

        try {
            processBuilder.start();
        } catch (Exception e) {
            LoggerWrapper.get().errorLog("Can't pin GA to task bar    ");
            LoggerWrapper.get().errorLog(e.toString());
        }

    }

    public void downloadBA() {
        File app = null;
        app = getAppForInstalation();
    }

    private File getAppForInstalation() {
        download = new DownloadBBContent();
        download.downloadContentBAccessBuild();
        File classpathRoot = new File(download.contentBuidPath);
        blackberryAccessVersion = download.buildNum;
        return classpathRoot;
    }

    public void downloadBWork() {
        File app = null;
        app = getBlackWorkCrxForInstalation();
    }

    private File getBlackWorkCrxForInstalation() {
        download = new DownloadBBContent();
        download.downloadContentBWorkSBuild();
        File classpathRoot = new File(download.contentBuidPath);
        blackberryWorkVersion = download.buildNum;
        return classpathRoot;
    }
}

