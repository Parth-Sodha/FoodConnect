package com.tm109.foodconnect.fragments.profile;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tm109.foodconnect.R;
import com.tm109.foodconnect.activity.LoginActivity;
import com.tm109.foodconnect.helper.AppConstant;
import com.tm109.foodconnect.helper.BaseFragment;
import com.tm109.foodconnect.helper.SecurePreferences;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileFragment extends BaseFragment {

    @BindView(R.id.txt_user_nm)
    TextView txt_user_nm;
    @BindView(R.id.txt_mobile)
    TextView txt_mobile;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initView() {

    }

    @OnClick(R.id.rel_edt_profile)
    void clickProfile() {
        loadFragmentFull(new EditProfileFragment(), "EditProfileFragment");
    }

    @OnClick(R.id.rel_logout)
    void clickLogout() {
        Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.custom_dialog_layout);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCancelable(false);
        TextView btn_cancel = dialog.findViewById(R.id.btn_dialog_no);
        TextView btn_yesDelete = dialog.findViewById(R.id.btn_dialog_yes);
        btn_yesDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SecurePreferences.clearSecurepreference(getActivity());
                SecurePreferences.savePreferences(activity, AppConstant.IS_LOGIN,false);
                getActivity().finish();
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void onBack() {

    }
}
