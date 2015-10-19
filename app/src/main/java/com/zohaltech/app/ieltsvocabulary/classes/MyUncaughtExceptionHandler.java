package com.zohaltech.app.ieltsvocabulary.classes;

import android.os.Environment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class MyUncaughtExceptionHandler implements java.lang.Thread.UncaughtExceptionHandler {
    public MyUncaughtExceptionHandler() {
    }

    public static void logException(final Throwable exception) {
        new Thread(new Runnable() {
            public void run() {
                StringBuilder errorReport = new StringBuilder();
                StringWriter stackTrace = new StringWriter();
                exception.printStackTrace(new PrintWriter(stackTrace));
                errorReport.append("************ CAUSE OF ERROR ************\n\n");
                errorReport.append(stackTrace.toString());
                errorReport.append("--------------------------------------------------\n");
                writeToFile(errorReport.toString());
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(10);
            }
        }).start();
    }

    private static void writeToFile(String errorText) {
        try {
            File file = new File(Environment.getExternalStorageDirectory(), "corevocabulary_log.txt");
            if (!file.exists()) {
                // file.mkdirs();
                file.createNewFile();
            } else {
                if (file.length() > (5 * 1024 * 1024)) {
                    if (file.delete()) {
                        file.createNewFile();
                    }
                }
            }
            FileWriter writer = new FileWriter(file, true);
            writer.append(errorText);
            writer.flush();
            writer.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void uncaughtException(Thread thread, final Throwable exception) {
        logException(exception);
    }

}

