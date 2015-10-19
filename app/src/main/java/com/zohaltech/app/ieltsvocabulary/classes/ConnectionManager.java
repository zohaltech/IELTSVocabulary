package com.zohaltech.app.ieltsvocabulary.classes;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionManager {

    public static int TYPE_NOT_CONNECTED = 0;
    public static int TYPE_WIFI          = 1;
    public static int TYPE_MOBILE        = 2;

    public static int getConnectivityStatus() {
        ConnectivityManager cm = (ConnectivityManager) App.context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return TYPE_WIFI;
            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return TYPE_MOBILE;
        }
        return TYPE_NOT_CONNECTED;
    }

    private static boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) App.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

    public static InternetStatus getInternetStatus() {
        InternetStatus result = InternetStatus.NotConnected;
        if (isNetworkAvailable()) {
            try {
                HttpURLConnection urlc = (HttpURLConnection) (new URL("http://clients3.google.com/generate_204").openConnection());
                urlc.setRequestProperty("User-Agent", "Android");
                urlc.setRequestProperty("Connection", "close");
                urlc.setConnectTimeout(1500);
                urlc.connect();
                if (urlc.getResponseCode() == 204 &&
                    urlc.getContentLength() == 0) {
                    result = InternetStatus.Connected;
                } else {
                    result = InternetStatus.NotConnected;
                }
            } catch (Exception e) {
                result = InternetStatus.Error;
            }
        }
        return result;
    }


    public enum InternetStatus {
        Connected,
        NotConnected,
        Error
    }
}