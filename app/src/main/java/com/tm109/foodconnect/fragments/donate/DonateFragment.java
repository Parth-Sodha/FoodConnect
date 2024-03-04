package com.tm109.foodconnect.fragments.donate;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.loopj.android.http.RequestParams;
import com.tm109.foodconnect.R;
import com.tm109.foodconnect.activity.LoginActivity;
import com.tm109.foodconnect.fragments.LoginFragment;
import com.tm109.foodconnect.fragments.home.HomeFragment;
import com.tm109.foodconnect.helper.AppConstant;
import com.tm109.foodconnect.helper.BaseFragment;
import com.tm109.foodconnect.helper.CallBack;
import com.tm109.foodconnect.helper.DbHelper;
import com.tm109.foodconnect.helper.WebApiHelper;
import com.tm109.foodconnect.model.DonorModel;
import com.tm109.foodconnect.model.NgoModel;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DonateFragment extends BaseFragment {

    @BindView(R.id.edt_name)
    EditText edt_name;
    @BindView(R.id.edt_mobile)
    EditText edt_mobile;
    @BindView(R.id.edt_email)
    EditText edt_email;
    @BindView(R.id.edt_state)
    EditText edt_state;
    @BindView(R.id.edt_city)
    EditText edt_city;
    @BindView(R.id.edt_address)
    EditText edt_address;
    @BindView(R.id.edt_description)
    EditText edt_description;
    @BindView(R.id.txt_coocking_date)
    TextView txt_coocking_date;
    @BindView(R.id.txt_expire_date)
    TextView txt_expire_date;
    @BindView(R.id.lin_veg)
    LinearLayout lin_veg;
    @BindView(R.id.lin_non_veg)
    LinearLayout lin_non_veg;
    @BindView(R.id.radio_group_type)
    RadioGroup radio_group_type;
    @BindView(R.id.radio_btn_veg)
    RadioButton radio_btn_veg;
    @BindView(R.id.radio_btn_non_veg)
    RadioButton radio_btn_non_veg;
    String cookDate = "";
    String expireDate = "";

    DbHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_donate, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void initView() {
        cookDate = AppConstant.getCurrentDateTime();
        txt_coocking_date.setText(AppConstant.getDatetimeFormat(cookDate));
        dbHelper = new DbHelper(activity);

//        donorModel.setOrgName(edt_org_name.getText().toString().trim());
//        donorModel.setContactName(edt_contact_name.getText().toString().trim());
//        donorModel.setEmail(edt_email.getText().toString().trim());
//        donorModel.setMobile(edt_mobile.getText().toString().trim());
//        donorModel.setPassword(edt_password.getText().toString().trim());
//        donorModel.setState(edt_state.getText().toString().trim());
//        donorModel.setCity(edt_city.getText().toString().trim());
//        donorModel.setAddress(edt_address.getText().toString().trim());
//        donorModel.setDescription(edt_description.getText().toString().trim());
//        long insert = dbHelper.insertRecord(ngoModel);

//        if(insert > 0) {
//            showToast("Insert Successfully!");
//        }

//        if(radio_btn_veg.isChecked())
//        {
//            radio_btn_veg.setChecked(true);
//            radio_btn_non_veg.setChecked(false);
//            lin_veg.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.primary_border_rounded_5));
//            lin_non_veg.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.default_border_rounded_5));
//        }
//        else
//        {
//            radio_btn_veg.setChecked(false);
//            radio_btn_non_veg.setChecked(true);
//            lin_non_veg.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.primary_border_rounded_5));
//            lin_veg.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.default_border_rounded_5));
//        }
//        radio_group_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                if (radio_btn_veg.isChecked()) {
//                    radio_btn_non_veg.setChecked(false);
//                }
//                else {
//                    radio_btn_veg.setChecked(false);
//                }
//            }

        radio_btn_veg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    radio_btn_veg.setChecked(true);
                    radio_btn_non_veg.setChecked(false);
                    lin_veg.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.primary_border_rounded_5));
                    lin_non_veg.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.default_border_rounded_5));
                }
            }
        });
        radio_btn_non_veg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    radio_btn_non_veg.setChecked(true);
                    radio_btn_veg.setChecked(false);
                    lin_non_veg.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.primary_border_rounded_5));
                    lin_veg.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.default_border_rounded_5));
                }
            }
        });

}

    @OnClick(R.id.txt_coocking_date)
    void clickCookDate() {
        final Calendar c = Calendar.getInstance();

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String selectedDate = AppConstant.getddmmyyyyDateOnly(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                cookDate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                txt_coocking_date.setText(selectedDate);
            }
        }, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    @OnClick(R.id.txt_expire_date)
    void clickExpireDate() {
        final Calendar c = Calendar.getInstance();

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String selectedDate = AppConstant.getddmmyyyyDateOnly(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                expireDate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                txt_expire_date.setText(selectedDate);
            }
        }, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();

    }

    @OnClick(R.id.btn_submit)
    void clickSubmit() {
        if (isValidate()) {
            callAddDonate();
        }
    }

    private void callAddDonate() {
        DonorModel donorModel = new DonorModel();
        donorModel.setName(edt_name.getText().toString().trim());
        donorModel.setMobile(edt_mobile.getText().toString().trim());
        donorModel.setEmail(edt_email.getText().toString().trim());
        donorModel.setState(edt_state.getText().toString().trim());
        donorModel.setCity(edt_city.getText().toString().trim());
        donorModel.setAddress(edt_address.getText().toString().trim());
        donorModel.setType(edt_description.getText().toString().trim());
        donorModel.setCookDate(cookDate.trim());
        donorModel.setExpireDate(expireDate.trim());
        donorModel.setDescription(edt_description.getText().toString().trim());
        long insert = dbHelper.insertDonorRecord(donorModel);

        if(insert > 0) {
            showToast("Success");
            loadFragmentMain(new HomeFragment(), "HomeFragment");
        }

    }

    private boolean isValidate() {
        if (edt_name.getText().toString().trim().isEmpty()) {
            showToast("Please Enter Name");
            return false;
        } else if (edt_mobile.getText().toString().trim().isEmpty()) {
            showToast("Please Enter Mobile");
            return false;
        } else if (edt_email.getText().toString().trim().isEmpty()) {
            showToast("Please Enter Email");
            return false;
        } else if (edt_state.getText().toString().trim().isEmpty()) {
            showToast("Please Enter State");
            return false;
        } else if (edt_city.getText().toString().trim().isEmpty()) {
            showToast("Please Enter City");
            return false;
        } else if (edt_address.getText().toString().trim().isEmpty()) {
            showToast("Please Enter Address");
            return false;
        } else if (txt_expire_date.getText().toString().trim().isEmpty()) {
            showToast("Please Select Expire Date");
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onBack() {

    }
}
