package com.tm109.foodconnect.helper;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.MySSLSocketFactory;
import com.loopj.android.http.RequestParams;
import com.tm109.foodconnect.activity.SplashActivity;

import java.security.KeyStore;
import java.util.Objects;

import cz.msebera.android.httpclient.Header;

public class WebApiHelper {
    public static void callPostApi(Activity activity, String url, RequestParams params, boolean isLoading, CallBack callback) {

        url = url.replace(" ", "%20");
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization", "Bearer " + SecurePreferences.getStringPreference(activity, AppConstant.API_TOKEN));
        client.addHeader("Accept", "application/json");
        client.setTimeout(60000);
        try {
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(null, null);
            MySSLSocketFactory sf = new MySSLSocketFactory(trustStore);
            sf.setHostnameVerifier(MySSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            client.setSSLSocketFactory(sf);
        } catch (Exception e) {
            e.printStackTrace();
        }
        client.post(AppConstant.BASE_URL + url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                if (isLoading) {
                    AppConstant.showProgressDialog(activity);
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (isLoading)
                    AppConstant.hideProgressDialog();
                callback.onResponse(new String(responseBody));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                if (isLoading)
                    AppConstant.hideProgressDialog();
                if (!Objects.requireNonNull(error.getLocalizedMessage()).equalsIgnoreCase("Unauthorized"))
                {
                    Toast.makeText(activity, Objects.requireNonNull(error.getLocalizedMessage()), Toast.LENGTH_SHORT).show();
                }
                if (statusCode == 401)
                {
                    SecurePreferences.clearSecurepreference(activity);

                    activity.finish();
                    activity.startActivity(new Intent(activity, SplashActivity.class));
                }
            }
        });
    }
    public static void callGetApi(Activity activity, String url, boolean isLoading, CallBack callBack) {
        url = url.replace(" ", "%20");
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization", "Bearer " + SecurePreferences.getStringPreference(activity, AppConstant.API_TOKEN));
        client.addHeader("Accept", "application/json");
        client.setTimeout(60000);
        try {
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(null, null);
            MySSLSocketFactory sf = new MySSLSocketFactory(trustStore);
            sf.setHostnameVerifier(MySSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            client.setSSLSocketFactory(sf);
        } catch (Exception e) {
            e.printStackTrace();
        }
        client.get(AppConstant.BASE_URL + url, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                if (isLoading) {
                    AppConstant.showProgressDialog(activity);
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (isLoading)
                    AppConstant.hideProgressDialog();
                callBack.onResponse(new String(responseBody));

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                if (isLoading)
                    AppConstant.hideProgressDialog();
                callBack.onResponse(error.getLocalizedMessage());
                if (!Objects.requireNonNull(error.getLocalizedMessage()).equalsIgnoreCase("Unauthorized"))
                {
                    Toast.makeText(activity, Objects.requireNonNull(error.getLocalizedMessage()), Toast.LENGTH_SHORT).show();
                }
                if (statusCode == 401) {
                    SecurePreferences.clearSecurepreference(activity);

                    activity.finish();
                    activity.startActivity(new Intent(activity, SplashActivity.class));
                }
            }
        });
    }
}