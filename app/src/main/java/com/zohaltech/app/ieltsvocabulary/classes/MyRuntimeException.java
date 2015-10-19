package com.zohaltech.app.ieltsvocabulary.classes;

import java.io.PrintStream;

public class MyRuntimeException extends RuntimeException {

    public MyRuntimeException() {
        super();
    }

    public MyRuntimeException(String detailMessage) {
        super(detailMessage);
    }

    public MyRuntimeException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public MyRuntimeException(Throwable throwable) {
        super(throwable);
    }

    @Override
    public void printStackTrace(PrintStream err) {
        super.printStackTrace(err);
        MyUncaughtExceptionHandler.logException(this);
    }
}
