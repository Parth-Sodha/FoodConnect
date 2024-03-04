package com.tm109.foodconnect.helper;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.inputmethod.InputMethodManager;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.tm109.foodconnect.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AppConstant {

    public static String BASE_URL = "https://food-connect-backend.vercel.app/";
    public static String IMAGE_URL = "https://food-connect-backend.vercel.app/upload/";

//    public static String BASE_URL = "http://10.130.148.207:8080/";
//    public static String IMAGE_URL = "http://10.130.148.207:8080/upload/";

    public static String REGISTER_API = "ngo/register";
    public static String LOGIN_API = "ngo/login";
    public static String GET_NGO_LIST_API = "ngo/all-ngos";
    public static String DONATE_FORM_API = "donor/donate";
    public static String GET_DONOR_LIST_API = "donor/all-donate";

    public static String IS_LOGIN = "is_login";
    public static String USER_TYPE = "user_type";
    public static String USER_ID ="user_id";
    public static final String USER_MOBILE = "user_mobile";
    public static final String USER_NAME = "user_name";
    public static final String USER_DETAILS = "user_details";
    public static final String ADMIN_DETAILS = "admin_details";

    public static final String API_TOKEN = "token";

    // cms
    public static final String TERMS_CONDITION = "terms_and_conditions";
    public static final String CONTACT_US = "contact_us";
    public static final String PRIVACY_POLICY = "privacy_policy";
    public static final String ABOUT = "about_us";

    public static final String FCM_TOKEN = "fcm_token";
    public static final int SUCCESS = 2;
    public static final int ERROR = 1;
    public static int REQUEST_PICK = 100;
    public static int CAMERA_REQESTCODE = 101;
    public static ProgressDialog dialog;

    public static String CITY = "city";
    public static String ADDRESS = "address";
    public static final String PLACE_API_TYPE = "place_api_key";
    public static final String GOOGLE_API_KEY = "google_api_key";
    public static final String LOAD_HOME_DATA = "load_home_data";
    public static final String USER_GST ="user_gst" ;
    public static final String CUSTOMER_ADD_CASH = "customer/add-cash";

    public static void hideKeyboard(FragmentActivity activity) {
        if (activity.getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                inputMethodManager = (InputMethodManager) activity.getSystemService(INPUT_METHOD_SERVICE);
            }
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    public static void showProgressDialog(Context context) {
        dialog = new ProgressDialog(context);
        if (dialog.getWindow() != null)
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();
        dialog.setContentView(R.layout.custom_progress);
    }

    public static void hideProgressDialog() {
        if (dialog != null && dialog.isShowing())
            dialog.dismiss();
    }

    public static void runLayoutAnimation(RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.rcy_item_animation);
        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    public static String getCurrentDateTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        return dateFormat.format(calendar.getTime());
    }

    public static String getddmmyyyyDateOnly(String currentDate) {
        try {
            SimpleDateFormat outputFmt = new SimpleDateFormat("yyyy-MM-dd");
            Date date = outputFmt.parse(currentDate);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(" dd-MM-yy");
            return simpleDateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "-";
        }
    }

    public static String getDatetimeFormat(String sDate) {
        try {
            SimpleDateFormat sdfSource = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdfSource.parse(sDate);
            SimpleDateFormat sdfDestination = new SimpleDateFormat("dd-MM-yy");
            return sdfDestination.format(date);
        } catch (Exception e) {
            return "";
        }
    }

}