package com.good.www1.win.asserts;

import com.good.www1.win.tools.CaptureScreenImage;

public class ScreenCapturingCustomException extends GeneralCustomException {
    private static final long serialVersionUID = 1L;

    // Classic constructor with a message of error.
    public ScreenCapturingCustomException(String message) {
        super(message);
        takeScreenshot();
    }

    public ScreenCapturingCustomException(String message, Throwable e) {
        super(message, e);
        takeScreenshot();
    }

    private void takeScreenshot() {
        LoggerWrapper.get().infoLog("Screenshot filename is "
                        + CaptureScreenImage.get().captureAndSaveScreen());
    }

}
