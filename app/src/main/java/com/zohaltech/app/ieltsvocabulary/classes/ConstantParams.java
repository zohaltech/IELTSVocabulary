package com.zohaltech.app.ieltsvocabulary.classes;


import com.zohaltech.app.ieltsvocabulary.R;

public final class ConstantParams {
    private static String apiSecurityKey = App.context.getString(R.string.jan);

    public static String getApiSecurityKey() {
        return apiSecurityKey;
    }
}