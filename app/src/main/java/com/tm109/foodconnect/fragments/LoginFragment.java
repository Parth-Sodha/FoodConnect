package com.tm109.foodconnect.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tm109.foodconnect.R;
import com.tm109.foodconnect.activity.LoginActivity;
import com.tm109.foodconnect.activity.MainActivity;
import com.tm109.foodconnect.helper.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginFragment extends BaseFragment {

    @BindView(R.id.txt_register)
    TextView txt_register;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initView() {

    }

    @OnClick(R.id.txt_register)
    void clickRegister() {
        ((LoginActivity)getActivity()).loadLoginFragment(new RegisterFragment(),"RegisterFragment");
//        loadFragmentFull(new RegisterFragment(), "RegisterFragment");
    }

    @OnClick(R.id.btn_login)
    void clickBtnLogin() {
        if(isValidate()) {
            callLoginApi();
        }
    }

    private void callLoginApi() {
    }

    private boolean isValidate() {
        return false;
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
