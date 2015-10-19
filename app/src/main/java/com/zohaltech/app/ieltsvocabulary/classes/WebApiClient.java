package com.zohaltech.app.ieltsvocabulary.classes;

import android.os.Build;
import android.util.Log;

import com.zohaltech.app.ieltsvocabulary.BuildConfig;
import com.zohaltech.app.ieltsvocabulary.R;
import com.zohaltech.app.ieltsvocabulary.data.SystemSettings;
import com.zohaltech.app.ieltsvocabulary.entities.SystemSetting;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

public class WebApiClient {
    private static final String HOST_URL = App.context.getString(R.string.host_name);
    private JSONObject jsonObject;

    public static void sendUserData(WebApiClient.PostAction postAction, String token) {
        WebApiClient webApiClient = new WebApiClient();
        webApiClient.postSubscriberData(postAction, token);
    }

    private JSONObject getJsonObject() {
        return jsonObject;
    }

    private void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public void postSubscriberData(final PostAction action, final String token) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SystemSetting setting = SystemSettings.getCurrentSettings();
                    JSONObject jsonObject = new JSONObject();

                    if (action == PostAction.INSTALL) {
                        if (!setting.getInstalled()) {
                            if (ConnectionManager.getInternetStatus() == ConnectionManager.InternetStatus.Connected) {
                                jsonObject.accumulate("SecurityKey", ConstantParams.getApiSecurityKey());
                                jsonObject.accumulate("AppId", 2);
                                jsonObject.accumulate("DeviceId", Helper.getDeviceId());
                                jsonObject.accumulate("DeviceBrand", Build.MANUFACTURER);
                                jsonObject.accumulate("DeviceModel", Build.MODEL);
                                jsonObject.accumulate("AndroidVersion", Build.VERSION.RELEASE);
                                jsonObject.accumulate("ApiVersion", Build.VERSION.SDK_INT);
                                jsonObject.accumulate("OperatorId", Helper.getOperator().ordinal());
                                jsonObject.accumulate("IsPurchased", false);
                                jsonObject.accumulate("MarketId", App.market);
                                jsonObject.accumulate("AppVersion", BuildConfig.VERSION_CODE);
                                jsonObject.accumulate("PurchaseToken", token);
                                setJsonObject(jsonObject);
                                Boolean result = post(getJsonObject());
                                setting.setInstalled(result);
                                SystemSettings.update(setting);
                            }
                        }
                    } else {
                        if (!setting.getPremium()) {
                            if (ConnectionManager.getInternetStatus() == ConnectionManager.InternetStatus.Connected) {
                                jsonObject.accumulate("SecurityKey", ConstantParams.getApiSecurityKey());
                                jsonObject.accumulate("AppId", 2);
                                jsonObject.accumulate("DeviceId", Helper.getDeviceId());
                                jsonObject.accumulate("DeviceBrand", Build.MANUFACTURER);
                                jsonObject.accumulate("DeviceModel", Build.MODEL);
                                jsonObject.accumulate("AndroidVersion", Build.VERSION.RELEASE);
                                jsonObject.accumulate("ApiVersion", Build.VERSION.SDK_INT);
                                jsonObject.accumulate("OperatorId", Helper.getOperator().ordinal());
                                jsonObject.accumulate("IsPurchased", true);
                                jsonObject.accumulate("MarketId", App.market);
                                jsonObject.accumulate("AppVersion", BuildConfig.VERSION_CODE);
                                jsonObject.accumulate("PurchaseToken", token);
                                setJsonObject(jsonObject);
                                Boolean result = post(getJsonObject());
                                setting.setPremium(result);
                                SystemSettings.update(setting);
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }

    private Boolean post(JSONObject jsonObject) {
        try {
            // 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();
            // 2. make POST request to the given URL
            HttpPost httpPost = new HttpPost(HOST_URL);
            String json;
            // 3. build jsonObject
            //JSONObject jsonObject = getJsonObject();

            // 4. convert JSONObject to JSON to String
            json = jsonObject.toString();
            // ** Alternative way to convert Person object to JSON string using Jackson Lib
            // ObjectMapper mapper = new ObjectMapper();
            // json = mapper.writeValueAsString(person);
            // 5. set json to StringEntity
            StringEntity stringEntity = new StringEntity(json);
            // 6. set httpPost Entity
            httpPost.setEntity(stringEntity);
            // 7. Set some headers to inform server about the type of the content
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
            // httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");


            HttpResponse httpResponse = httpclient.execute(httpPost);

            StatusLine statusLine = httpResponse.getStatusLine();
            int resultCode = statusLine.getStatusCode();
            return resultCode == 200;

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }
        return false;
    }

    public enum PostAction {
        INSTALL,
        REGISTER
    }
}
