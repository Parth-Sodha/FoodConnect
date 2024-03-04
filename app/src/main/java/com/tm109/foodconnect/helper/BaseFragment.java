package com.tm109.foodconnect.helper;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.tm109.foodconnect.R;

public abstract class BaseFragment extends Fragment
{
    public AppCompatActivity activity;
    @Nullable
    @Override
    public abstract View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    public abstract void initView();

    public abstract void onBack();

    public void loadFragmentMain(Fragment fragment, String tag) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.frame_main, fragment, tag).addToBackStack(null).commit();
    }

    public void loadFragmentFull(Fragment fragment, String tag) {
        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.slide_in, R.anim.back_slide_in, R.anim.slide_out, R.anim.back_slide_out);
        ft.add(R.id.frame_full, fragment, tag).addToBackStack(null).commit();
    }

    @Override
    public void onAttach(@NonNull Context context)
    {
        super.onAttach(context);
        activity = (AppCompatActivity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

}
