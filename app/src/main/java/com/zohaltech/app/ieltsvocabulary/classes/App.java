package com.zohaltech.app.ieltsvocabulary.classes;

import android.app.Activity;
import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Handler;
import android.preference.PreferenceManager;

import java.util.Locale;

public class App extends Application {

    public static final int MARKET_BAZAAR = 0;
    public static final int MARKET_CANDO  = 1;
    public static final int MARKET_MYKET  = 2;
    public static final int MARKET_PLAY   = 3;

    public static Context           context;
    public static Activity          currentActivity;
    public static SharedPreferences preferences;
    //public static Typeface          englishFont;
    //public static Typeface          englishFontBold;
    public static Typeface          persianFont;
    public static Typeface          persianFontBold;
    public static Handler           handler;
    public static int               screenWidth;
    public static int               screenHeight;
    public static Locale            locale;
    public static int               market;
    public static String            marketName;
    public static String            marketPackage;
    public static String            marketAction;
    //public static String            marketUri;
    public static String            marketWebsiteUri;
    public static String            marketDeveloperUri;
    public static String            marketPollUri;
    public static String            marketPollIntent;
    public static String            marketPublicKey;
    public static SearchCache       searchCache;

    public static NotificationManager notificationManager;

    public static void setAppLocal() {
        locale = new Locale("en");
        Locale.setDefault(locale);
        Configuration config = context.getResources().getConfiguration();
        config.locale = locale;
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        //englishFont = Typeface.createFromAsset(context.getAssets(), "fonts/exo.ttf");
        //englishFontBold = Typeface.createFromAsset(context.getAssets(), "fonts/exo.ttf");
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        persianFont = Typeface.createFromAsset(context.getAssets(), "fonts/byekan.ttf");
        persianFontBold = Typeface.createFromAsset(context.getAssets(), "fonts/byekan.ttf");
        handler = new Handler();
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;
        setAppLocal();
        SearchCache.initialise();

        //todo : set market here and in manifest
        market = MARKET_BAZAAR;
        marketName = "بازار";
        marketPackage = "com.farsitel.bazaar";
        marketAction = "ir.cafebazaar.pardakht.InAppBillingService.BIND";
        //marketUri = "bazaar://details?id=" + getPackageName();
        marketWebsiteUri = "http://cafebazaar.ir/app/" + getPackageName();
        marketDeveloperUri = "bazaar://collection?slug=by_author&aid=zohaltech";
        marketPollUri = "bazaar://details?id=" + getPackageName();
        marketPollIntent = Intent.ACTION_EDIT;
        marketPublicKey = ConstantParams.getBazaarPublicKey();

        //market = MARKET_CANDO;
        //marketName = "کندو";
        //marketPackage = "com.ada.market";
        //marketAction = "com.ada.market.service.payment.BIND";
        ////marketUri = "cando://details?id=" + getPackageName();
        //marketWebsiteUri = "http://cando.asr24.com/app.jsp?package=" + getPackageName();
        //marketDeveloperUri = "cando://publisher?id=zohaltech@gmail.com";
        //marketPollUri = "cando://leave-review?id=" + getPackageName();
        //marketPollIntent = Intent.ACTION_VIEW;
        //marketPublicKey = ConstantParams.getCandoPublicKey();

        //market = MARKET_MYKET;
        //marketName = "مایکت";
        //marketPackage = "ir.mservices.market";
        //marketAction = "ir.mservices.market.InAppBillingService.BIND";
        ////marketUri = "myket://application/#Intent;scheme=myket;package= + getPackageName() + ;end";
        //marketWebsiteUri = "http://myket.ir/Appdetail.aspx?id=" + getPackageName();
        //marketDeveloperUri = "http://myket.ir/DeveloperApps.aspx?Packagename=" + getPackageName();
        //marketPollUri = "myket://comment/#Intent;scheme=comment;package=" + getPackageName() + ";end";
        //marketPollIntent = Intent.ACTION_VIEW;
        //marketPublicKey = ConstantParams.getMyketPublicKey();

        //market = MARKET_PLAY;
        //marketName = "Google Play";
        //marketPackage = "com.android.vending";
        //marketAction = "com.android.vending.billing.InAppBillingService.BIND";
        //marketUri = "market://details?id=" + getPackageName();
        //marketWebsiteUri = "" + getPackageName();
        //marketPollIntent = Intent.ACTION_EDIT;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
    }
}
