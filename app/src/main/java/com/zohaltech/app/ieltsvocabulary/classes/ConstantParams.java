package com.zohaltech.app.ieltsvocabulary.classes;


import com.zohaltech.app.ieltsvocabulary.R;

public final class ConstantParams {
    private static String apiSecurityKey  = App.context.getString(R.string.jan);
    private static String secretKey       = App.context.getString(R.string.sdj);
    private static String iv              = App.context.getString(R.string.mongol);
    private static String bazaarPublicKey = App.context.getString(R.string.bare_sala);
    private static String candoPublicKey  = App.context.getString(R.string.nice_ass);
    private static String myketPublicKey  = App.context.getString(R.string.doozlu_bala);
    private static String playPublicKey   = App.context.getString(R.string.bare_sala);

    public static String getSecretKey() {
        return secretKey;
    }

    public static String getIv() {
        return iv;
    }

    public static String getBazaarPublicKey() {
        return bazaarPublicKey;
    }

    public static String getCandoPublicKey() {
        return candoPublicKey;
    }

    public static String getMyketPublicKey() {
        return myketPublicKey;
    }

    public static String getPlayPublicKey() {
        return playPublicKey;
    }

    public static String getApiSecurityKey() {
        return apiSecurityKey;
    }
}
