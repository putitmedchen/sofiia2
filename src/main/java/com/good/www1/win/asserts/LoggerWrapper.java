package com.good.www1.win.asserts;

import org.testng.Reporter;

public class LoggerWrapper {
    private static volatile LoggerWrapper instance = null;

    private LoggerWrapper() {
    }

    public static LoggerWrapper get() {
        if (instance == null) {
            synchronized (LoggerWrapper.class) {
                if (instance == null) {
                    instance = new LoggerWrapper();
                }
            }
        }
        return instance;
    }

    public void errorLog(String message) {
        Reporter.log("<br>[ERROR] " + message);
        System.out.println("\n" + message + "\n");
    }

    public void infoLog(String message) {
        Reporter.log("<br>[INFO] " + message);
        System.out.println("\n" + message + "\n");
    }

    public void log(String message) {
        Reporter.log(message);
        System.out.println("\n" + message + "\n");
    }

    public String logLinkToFolder(String path) {
        String newPath = path.replace("/", "\\\\");
        Reporter.log("<a href=\"file://" + newPath + "\">" + path + "</a>");
        String reporterPath = "<a href=\"file://" + newPath + "\">" + path + "</a>";
        return reporterPath;
    }
}
