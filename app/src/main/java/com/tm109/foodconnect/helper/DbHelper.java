package com.tm109.foodconnect.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.tm109.foodconnect.model.DonorModel;
import com.tm109.foodconnect.model.NgoModel;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {

    String NGO_MASTER = "ngo_master";
    String DONOR_MASTER = "donor_master";

    public DbHelper(@Nullable Context context) {
        super(context, "DONOR.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Registration
        String tbl1 = "create table NGO_MASTER(id integer primary key autoincrement, org_name text, contact_name text, " +
                "email text, password text, description text," +
                "mobile text, state text, city text, address text, logo text)";
        db.execSQL(tbl1);
        // Donation
        String tbl2 = "create table DONOR_MASTER(id integer primary key autoincrement, name text, mobile text, " +
                "email text, state text, city text, address text, description text, type text, ava_qty integer," +
                "cookdate text, expiredate text)";
        db.execSQL(tbl2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
//        db.execSQL("DROP TABLE IF EXISTS NGO_MASTER");
//        onCreate(db);
    }

    public long insertRecord(NgoModel ngoModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("org_name", ngoModel.getOrgName());
        values.put("contact_name", ngoModel.getContactName());
        values.put("email", ngoModel.getEmail());
        values.put("password", ngoModel.getPassword());
        values.put("description", ngoModel.getDescription());
        values.put("mobile", ngoModel.getMobile());
        values.put("state", ngoModel.getState());
        values.put("city", ngoModel.getCity());
        values.put("address", ngoModel.getAddress());
        values.put("logo", ngoModel.getLogo());
        long insert = db.insert("NGO_MASTER", null, values);
        db.close();
        return insert;
    }

    public ArrayList<NgoModel> getUserData() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<NgoModel> ngoList = new ArrayList<>();
        Cursor c = db.query(NGO_MASTER, null, null, null, null, null, null);
        for (int i = 0; i < c.getCount(); i++) {
            c.moveToNext();
            NgoModel ngoModel = new NgoModel();
            ngoModel.setId(c.getInt(0));
            ngoModel.setOrgName(c.getString(1));
            ngoModel.setContactName(c.getString(2));
            ngoModel.setEmail(c.getString(3));
            ngoModel.setPassword(c.getString(4));
            ngoModel.setDescription(c.getString(5));
            ngoModel.setMobile(c.getString(6));
            ngoModel.setState(c.getString(7));
            ngoModel.setCity(c.getString(8));
            ngoModel.setAddress(c.getString(9));
            ngoModel.setLogo(c.getString(10));
            ngoList.add(ngoModel);
        }
        return ngoList;
    }

    public NgoModel loginUser(NgoModel ngoModel) {
        ArrayList<NgoModel> userList = getUserData();
        for (NgoModel model: userList) {
            if(ngoModel.getEmail().equals(model.getEmail()) && ngoModel.getPassword().equals(model.getPassword())) {
                return model;
            }
        }
        return null;
    }

    public long insertDonorRecord(DonorModel ngoModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", ngoModel.getName());
        values.put("mobile", ngoModel.getMobile());
        values.put("email", ngoModel.getEmail());
        values.put("state", ngoModel.getState());
        values.put("city", ngoModel.getCity());
        values.put("address", ngoModel.getAddress());
        values.put("description", ngoModel.getDescription());
        values.put("type", ngoModel.getType());
        values.put("ava_qty", ngoModel.getAvailableQuantity());
        values.put("cookdate", ngoModel.getCookDate());
        values.put("expiredate", ngoModel.getExpireDate());
        long insert = db.insert("DONOR_MASTER", null, values);
        db.close();
        return insert;
    }

    public ArrayList<DonorModel> getDonorData() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<DonorModel> donorList = new ArrayList<>();
        Cursor c = db.query(NGO_MASTER, null, null, null, null, null, null);
        for (int i = 0; i < c.getCount(); i++) {
            c.moveToNext();
            DonorModel ngoModel = new DonorModel();
            ngoModel.setId(c.getInt(0));
            ngoModel.setEmail(c.getString(3));
            ngoModel.setDescription(c.getString(5));
            ngoModel.setMobile(c.getString(6));
            ngoModel.setState(c.getString(7));
            ngoModel.setCity(c.getString(8));
            ngoModel.setAddress(c.getString(9));
            donorList.add(ngoModel);
        }
        return donorList;
    }

}