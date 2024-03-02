package com.tm109.foodconnect.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.tm109.foodconnect.R;
import com.tm109.foodconnect.fragments.LoginFragment;
import com.tm109.foodconnect.helper.AppConstant;
import com.tm109.foodconnect.helper.SecurePreferences;

import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        if(SecurePreferences.getBooleanPreference(LoginActivity.this, AppConstant.IS_LOGIN))
        {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        replaceFragmentMain(new LoginFragment(), "LoginFragment");
    }

    public void replaceFragmentMain(Fragment fragment, String tag) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_login, fragment, tag).commit();
    }
    public void loadLoginFragment(Fragment fragment, String tag) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.frame_login, fragment, tag).commit();
    }

}