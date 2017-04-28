package com.good.www1.win.tools;

import com.good.www1.win.asserts.LoggerWrapper;
import com.good.www1.win.utils.PropertyManager;
import org.sikuli.script.Pattern;

import java.io.File;

/**
 * Class designed for searching image on the screen assuming list of saved
 * images which are made for different resolutions or environments.
 */
public final class SearchPattern {
    private static final int ONE_SECOND = 1000;
    private static final int HALF_SECOND = 500;
    /**
     * Max time which we are waiting for element on screen. Time out is 180 sec.
     */
    private int TIME_OUT = PropertyManager.getSearchTimeOut() * ONE_SECOND;

    /**
     * Executable project path.
     */
    private static final String ROOT_PATH = "/";
    private static final String DRIVE_SEPARATOR = ":";

    /**
     * Classic implementation of singletone pattern for creation of Sikuli
     * screen.
     */
    private static volatile SearchPattern instance = null;
    private float similarity = (float) 0.8;

    /**
     * Classic implementation of singletone pattern for creation of Sikuli
     * screen. We need to create default empty constructor not to allow compiler
     * to create hit own constructor which will be public and which breaks logic
     * of singletone as it should allow to create just one object.
     */
    private SearchPattern() {
    }

    /**
     * Classic implementation of singletone pattern for creation of Sikuli
     * screen.
     */
    public static SearchPattern get() {
        if (instance == null) {
            synchronized (SearchPattern.class) {
                if (instance == null) {
                    instance = new SearchPattern();
                }
            }
        }
        return instance;
    }

    String getFileNameOfVisibleElement(String relativePath) {
        int iterationNumber = 0;
        String resultPath = new String();
        String absolutePath;
        if (relativePath.indexOf(DRIVE_SEPARATOR) > 0) {
            absolutePath = relativePath;
        } else {
            absolutePath = SearchPattern.class.getResource(ROOT_PATH).toString().substring(6) + relativePath;
        }
        File folder = new File(absolutePath);
        long currentTime = System.currentTimeMillis();
        while ((resultPath.isEmpty()) && (System.currentTimeMillis() - currentTime < TIME_OUT)) {
            for (File fileEntry : folder.listFiles()) {
                if (!fileEntry.isDirectory()) {
                    // System.out.println(fileEntry.getName());
                    if (SikuliScreenWrapper.get().getCurrentScreen()
                            .exists(new Pattern(absolutePath + fileEntry.getName()).similar(similarity)) != null) {
                        resultPath = absolutePath + fileEntry.getName();
                        break;
                    }
                }
            }
            if (resultPath.isEmpty()) {
                if (iterationNumber >= 1) {
                    LoggerWrapper.get().infoLog((resultPath.isEmpty() ? "waiting time in second = " + (new Long((System.currentTimeMillis() - currentTime) / 1000L).toString()) : resultPath));
                }
                if (iterationNumber < 1) {
                    LoggerWrapper.get().infoLog("NOT FOUND, file = "
                            + absolutePath.replace("target/test-classes/", "src/test/resources/"));
                    iterationNumber++;
                }

                try {
                    Thread.sleep(HALF_SECOND);
                } catch (InterruptedException e) {
                    // TODO Develop custom exception.
                    throw new RuntimeException(e);
                }
            }
        }
        LoggerWrapper.get()
                .infoLog("Found, file = " + (resultPath.isEmpty()
                        ? "not found, waiting time in second ="
                        + (new Long((System.currentTimeMillis() - currentTime) / 1000L).toString())
                        : relativePath + new CurrentTimeInSimpleFormat().getSimpleTime()) +
                        LoggerWrapper.get().logLinkToFolder(absolutePath.replace("target/test-classes/", "src/test/resources/")));
        return resultPath;
    }

    public boolean isVisibleElement(String relativePath) {
        return !(getFileNameOfVisibleElement(relativePath).isEmpty());
    }

    boolean isVisibleElement(Pattern pattern) {
        return (SikuliScreenWrapper.get().getCurrentScreen().exists(pattern) != null);
    }

    public int getTimeOut() {
        return TIME_OUT;
    }

    public void setTimeOut(int miliSeconds) {
        LoggerWrapper.get().infoLog(" Changing timeout" + "\r\n"
                + "++++++++++++++++++++++++++++++++++++++++++++++++++ Initial timeout: " + TIME_OUT);
        TIME_OUT = miliSeconds;
        LoggerWrapper.get().infoLog("++++++++++++++++++++++++++++++++++++++++++++++++++ New timeout : " + TIME_OUT);
    }

    public void setSimilarity(float similarityValue) {
        LoggerWrapper.get().infoLog(" Changing similarity" + "\r\n"
                + "++++++++++++++++++++++++++++++++++++++++++++++++++ Initial similarity: " + similarity);
        similarity = similarityValue;
        LoggerWrapper.get().infoLog("++++++++++++++++++++++++++++++++++++++++++++++++++ New similarity : " + similarity);
    }

    public float getSimilarity() {
        return similarity;
    }

}
