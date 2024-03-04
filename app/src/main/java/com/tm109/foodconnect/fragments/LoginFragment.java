package com.tm109.foodconnect.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.loopj.android.http.RequestParams;
import com.tm109.foodconnect.R;
import com.tm109.foodconnect.activity.LoginActivity;
import com.tm109.foodconnect.activity.MainActivity;
import com.tm109.foodconnect.helper.AppConstant;
import com.tm109.foodconnect.helper.BaseFragment;
import com.tm109.foodconnect.helper.CallBack;
import com.tm109.foodconnect.helper.DbHelper;
import com.tm109.foodconnect.helper.SecurePreferences;
import com.tm109.foodconnect.helper.WebApiHelper;
import com.tm109.foodconnect.model.NgoModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginFragment extends BaseFragment {

    @BindView(R.id.txt_register)
    TextView txt_register;
    @BindView(R.id.edt_email)
    EditText edt_email;
    @BindView(R.id.edt_password)
    EditText edt_password;
    DbHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void initView() {
        dbHelper = new DbHelper(activity);
    }

    @OnClick(R.id.txt_register)
    void clickRegister() {
        ((LoginActivity)getActivity()).loadLoginFragment(new RegisterFragment(),"RegisterFragment");
    }

    @OnClick(R.id.btn_login)
    void clickBtnLogin() {
        if(isValidate()) {
            callLoginApi();
        }
    }

    private void callLoginApi() {
        NgoModel ngoModel = new NgoModel();
        ngoModel.setEmail(edt_email.getText().toString().trim());
        ngoModel.setPassword(edt_password.getText().toString().trim());

        if(dbHelper.loginUser(ngoModel) != null) {
            dbHelper.getUserData();
            SecurePreferences.savePreferences(activity, AppConstant.IS_LOGIN,true);
            SecurePreferences.savePreferences(activity, AppConstant.USER_MOBILE, dbHelper.loginUser(ngoModel).getMobile());
            SecurePreferences.savePreferences(activity, AppConstant.USER_NAME,dbHelper.loginUser(ngoModel).getOrgName());
            SecurePreferences.savePreferences(activity, AppConstant.IS_LOGIN,true);
            SecurePreferences.savePreferences(activity, AppConstant.IS_LOGIN,true);
            startActivity(new Intent(getActivity(), MainActivity.class));
            getActivity().finish();
            showToast("Login Successfully!");
        }
        else {
            showToast("Invalid Data!");
        }
//        RequestParams params = new RequestParams();
//        params.put("email",edt_email.getText().toString().trim());
//        params.put("password",edt_password.getText().toString().trim());
//        WebApiHelper.callPostApi(activity, AppConstant.LOGIN_API, params, true, new CallBack() {
//            @Override
//            public void onResponse(String response) {
//
//            }
//        });
    }

    private boolean isValidate() {
        if(edt_email.getText().toString().trim().isEmpty()) {
            showToast("Please Enter Email");
            return false;
        }
        else if(edt_password.getText().toString().trim().isEmpty()) {
            showToast("Please Enter Password");
            return false;
        }
        else {
            return true;
        }
    }

    @OnClick(R.id.rel_skip)
    void clickSkip() {
        startActivity(new Intent(getActivity(), MainActivity.class));
        getActivity().finish();
    }

    @Override
    public void onBack() {
        getParentFragmentManager().popBackStack();
    }
}
