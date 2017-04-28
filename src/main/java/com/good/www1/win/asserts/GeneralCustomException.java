package com.good.www1.win.asserts;


public class GeneralCustomException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    // Classic constructor with a message of error.
    public GeneralCustomException(String message) {
        super(message);
        performLogging(message);
    }

    public GeneralCustomException(String message, Throwable e) {
        super(message, e);
        performLogging(message);
    }

    private void performLogging(String message) {
        LoggerWrapper.get().errorLog("GeneralCustomException: " + message);
    }

}
