package com.tm109.foodconnect.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.loopj.android.http.RequestParams;
import com.tm109.foodconnect.R;
import com.tm109.foodconnect.activity.LoginActivity;
import com.tm109.foodconnect.helper.BaseFragment;
import com.tm109.foodconnect.helper.WebApiHelper;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initView() {

    }

    @OnClick(R.id.btn_register)
    void clickBtnRegister() {
        if(isValidate()) {
            callRegisterApi();
        }
    }

    private boolean isValidate() {
        return false;
    }

    private void callRegisterApi() {
        ((LoginActivity)getActivity()).loadLoginFragment(new LoginFragment(),"LoginFragment");
    }

    @Override
    @OnClick(R.id.img_back)
    public void onBack() {
        getParentFragmentManager().popBackStack();
    }
}
