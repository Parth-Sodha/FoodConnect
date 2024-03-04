package com.tm109.foodconnect.fragments.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tm109.foodconnect.R;
import com.tm109.foodconnect.helper.BaseFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditProfileFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void initView() {

    }

    @Override
    @OnClick(R.id.img_back)
    public void onBack() {
        getParentFragmentManager().popBackStack();
    }
}
