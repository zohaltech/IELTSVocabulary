package com.zohaltech.app.ieltsvocabulary.classes;


import android.util.Base64;

public class Security {
    public static String decodeBase64(String text) {
        String result = "";
        try {
            byte[] data = Base64.decode(text, Base64.DEFAULT);
            result = new String(data, "UTF-8");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public static String encodeBase64(String text) {
        String result = "";
        try {
            byte[] data = text.getBytes("UTF-8");
            result = Base64.encodeToString(data, Base64.DEFAULT);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
