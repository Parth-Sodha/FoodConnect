package com.tm109.foodconnect.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.tm109.foodconnect.R;
import com.tm109.foodconnect.helper.AppConstant;
import com.tm109.foodconnect.helper.SecurePreferences;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                navigateNext();
            }
        },3000);

    }

    private void navigateNext() {
        if(SecurePreferences.getBooleanPreference(SplashActivity.this, AppConstant.IS_LOGIN))
        {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }
        else
        {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            finish();
        }
    }
}