package com.tm109.foodconnect.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.tm109.foodconnect.R;
import com.tm109.foodconnect.fragments.donate.DonateFragment;
import com.tm109.foodconnect.fragments.home.HomeFragment;
import com.tm109.foodconnect.fragments.profile.ProfileFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.img_home)
    ImageView img_home;
    @BindView(R.id.txt_home)
    TextView txt_home;
    @BindView(R.id.img_donate)
    ImageView img_donate;
    @BindView(R.id.txt_donate)
    TextView txt_donate;
    @BindView(R.id.img_profile)
    ImageView img_profile;
    @BindView(R.id.txt_profile)
    TextView txt_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    void initView() {
        homeClick();
    }

    public void replaceFragmentMain(Fragment fragment, String tag) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_main, fragment, tag).commit();
    }

    @OnClick(R.id.lin_home)
    public void homeClick() {
        replaceFragmentMain(new HomeFragment(), "HomeFragment");
        setDefaultColor();
        setThemeColor(1);
    }

    @OnClick(R.id.lin_donate)
    public void resourcesClick() {
        replaceFragmentMain(new DonateFragment(), "DonateFragment");
        setDefaultColor();
        setThemeColor(2);
    }

    @OnClick(R.id.lin_profile)
    public void profileClick() {
        replaceFragmentMain(new ProfileFragment(), "ProfileFragment");
        setDefaultColor();
        setThemeColor(3);
    }

    private void setThemeColor(int count) {
        switch (count) {
            case 1:
                img_home.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));
                txt_home.setTextColor(getResources().getColor(R.color.colorPrimary));
                break;
            case 2:
                img_donate.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));
                txt_donate.setTextColor(getResources().getColor(R.color.colorPrimary));
                break;
            case 3:
                img_profile.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));
                txt_profile.setTextColor(getResources().getColor(R.color.colorPrimary));
                break;
        }
    }

    private void setDefaultColor() {
        img_home.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.default_selected_clr)));
        txt_home.setTextColor(getResources().getColor(R.color.default_selected_clr));

        img_donate.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.default_selected_clr)));
        txt_donate.setTextColor(getResources().getColor(R.color.default_selected_clr));

        img_profile.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.default_selected_clr)));
        txt_profile.setTextColor(getResources().getColor(R.color.default_selected_clr));
    }

}