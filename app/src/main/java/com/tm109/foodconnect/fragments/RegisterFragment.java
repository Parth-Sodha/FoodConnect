package com.tm109.foodconnect.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.loopj.android.http.RequestParams;
import com.tm109.foodconnect.R;
import com.tm109.foodconnect.activity.LoginActivity;
import com.tm109.foodconnect.helper.AppConstant;
import com.tm109.foodconnect.helper.BaseFragment;
import com.tm109.foodconnect.helper.CallBack;
import com.tm109.foodconnect.helper.DbHelper;
import com.tm109.foodconnect.helper.WebApiHelper;
import com.tm109.foodconnect.model.NgoModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterFragment extends BaseFragment {

    @BindView(R.id.edt_org_name)
    EditText edt_org_name;
    @BindView(R.id.edt_contact_name)
    EditText edt_contact_name;
    @BindView(R.id.edt_mobile)
    EditText edt_mobile;
    @BindView(R.id.edt_email)
    EditText edt_email;
    @BindView(R.id.edt_city)
    EditText edt_city;
    @BindView(R.id.edt_state)
    EditText edt_state;
    @BindView(R.id.edt_password)
    EditText edt_password;
    @BindView(R.id.edt_address)
    EditText edt_address;
    @BindView(R.id.edt_description)
    EditText edt_description;
    @BindView(R.id.img_logo)
    ImageView img_logo;

    DbHelper dbHelper;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void initView() {
        dbHelper = new DbHelper(activity);
    }

    @OnClick(R.id.btn_register)
    void clickBtnRegister() {
        if(isValidate()) {
            callRegisterApi();
        }
    }

    private void callRegisterApi() {
        NgoModel ngoModel = new NgoModel();
        ngoModel.setOrgName(edt_org_name.getText().toString().trim());
        ngoModel.setContactName(edt_contact_name.getText().toString().trim());
        ngoModel.setEmail(edt_email.getText().toString().trim());
        ngoModel.setMobile(edt_mobile.getText().toString().trim());
        ngoModel.setPassword(edt_password.getText().toString().trim());
        ngoModel.setState(edt_state.getText().toString().trim());
        ngoModel.setCity(edt_city.getText().toString().trim());
        ngoModel.setAddress(edt_address.getText().toString().trim());
        ngoModel.setDescription(edt_description.getText().toString().trim());
        ngoModel.setLogo("");
        long insert = dbHelper.insertRecord(ngoModel);

        if(insert > 0) {
            ((LoginActivity)getActivity()).loadLoginFragment(new LoginFragment(),"LoginFragment");
            showToast("Registered Successfully!");
        }
//        RequestParams params = new RequestParams();
//        params.put("NameOfThePrimaryContactPerson", edt_name.getText().toString().trim());
//        params.put("email", edt_email.getText().toString().trim());
//        params.put("contactNumber", edt_mobile.getText().toString().trim());
//        params.put("state", edt_state.getText().toString().trim());
//        params.put("city", edt_city.getText().toString().trim());
//        params.put("address", edt_address.getText().toString().trim());
//        params.put("discription", edt_description.getText().toString().trim());
//        params.put("facebook", "");
//        params.put("instagram", "");
//        params.put("twitter", "");
//        params.put("organizationsWebsite", "");
//        params.put("logo", "");
//        WebApiHelper.callPostApi(activity, AppConstant.REGISTER_API, params, true, new CallBack() {
//            @Override
//            public void onResponse(String response) {
//                ((LoginActivity)getActivity()).loadLoginFragment(new LoginFragment(),"LoginFragment");
//            }
//        });
    }

    private boolean isValidate() {
        if(edt_org_name.getText().toString().trim().isEmpty()) {
            showToast("Please Enter Name");
            return false;
        }
        else if(edt_contact_name.getText().toString().trim().isEmpty()) {
            showToast("Please Contact Name");
            return false;
        }
        else if(edt_email.getText().toString().trim().isEmpty()) {
            showToast("Please Enter Email");
            return false;
        }
        else if(edt_mobile.getText().toString().trim().isEmpty()) {
            showToast("Please Enter Mobile");
            if(edt_mobile.getText().toString().length() < 10) {
                showToast("Please Enter 10 Digits");
                return false;
            }
            return false;
        }
        else if(edt_state.getText().toString().trim().isEmpty()) {
            showToast("Please Enter State");
            return false;
        }
        else if(edt_password.getText().toString().trim().isEmpty()) {
            showToast("Please Enter Password");
            return false;
        }
        else if(edt_city.getText().toString().trim().isEmpty()) {
            showToast("Please Enter City");
            return false;
        }
        else if(edt_address.getText().toString().trim().isEmpty()) {
            showToast("Please Enter Address");
            return false;
        }
        else if(edt_description.getText().toString().trim().isEmpty()) {
            showToast("Please Enter Description");
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    @OnClick(R.id.img_back)
    public void onBack() {
        getParentFragmentManager().popBackStack();
    }
}
