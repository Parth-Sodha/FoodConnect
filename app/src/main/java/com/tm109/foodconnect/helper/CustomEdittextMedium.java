package com.tm109.foodconnect.helper;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.EditText;

import androidx.annotation.RequiresApi;


public class CustomEdittextMedium extends EditText {


    public CustomEdittextMedium(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public CustomEdittextMedium(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public CustomEdittextMedium(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomEdittextMedium(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        Typeface customFont = FontCache.getTypeface("circular_std_medium.ttf", context);
        setTypeface(customFont);
    }
}
