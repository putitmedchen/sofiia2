package com.good.www1.win.asserts;

import com.good.www1.win.tools.CaptureScreenImage;
import org.testng.Assert;

public class AssertWrapper {
    private final String NEW_LINE = "\n";
    private final String APPEND_TEXT = "%sDescription: %s";
    private static final String SCREEN_IMAGE_PATH = "Screen image is located by the next path %s";
    private boolean summaryResult;
    private StringBuilder summaryDescription;
    private static volatile AssertWrapper instance = null;

    private AssertWrapper() {
        this.summaryResult = true;
        this.summaryDescription = new StringBuilder();
    }

    public static AssertWrapper get() {
        if (instance == null) {
            synchronized (AssertWrapper.class) {
                if (instance == null) {
                    instance = new AssertWrapper();
                }
            }
        }
        return instance;
    }

    public boolean getPassed() {
        return summaryResult;
    }

    public String getSummaryDescription() {
        return summaryDescription.toString();
    }

    public void verify(boolean pass, String errorText) {
        summaryResult = summaryResult && pass;
        if (!pass) {
            summaryDescription.append(String.format(APPEND_TEXT, NEW_LINE,
                    errorText));
            LoggerWrapper.get().errorLog(errorText);
            LoggerWrapper.get().infoLog(
                    String.format(SCREEN_IMAGE_PATH, CaptureScreenImage.get()
                            .captureAndSaveScreen()));
        }
    }

    public void addWarning(String warningText) {
        summaryDescription.append(String.format(APPEND_TEXT, NEW_LINE,
                warningText));
    }

    public void check() {
        boolean result = getPassed();
        summaryResult = true;
        String description = getSummaryDescription();
        Assert.assertTrue(result, description);
    }

    public LabelCriteria forElement(String path) {
        summaryDescription.append(path.toString());
        return LabelCriteria.get(path, this);
    }

    public void forElement(boolean pass) {
        verify(pass, "assertTrue");
    }

}
