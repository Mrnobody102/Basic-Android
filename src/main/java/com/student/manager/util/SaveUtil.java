package com.student.manager.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

@SuppressLint("CommitPrefEdits")
public class SaveUtil {
    public  static SharedPreferences sharedPreferences;

    public static void init(Context context){
        sharedPreferences = context.getSharedPreferences("data_quanly_manager", Context.MODE_PRIVATE);
    }

    public static void setTaikoan(String data) {
        sharedPreferences.edit().putString("local_taikhoan", data).apply();
    }
    public static String getTaikoan() {
        return sharedPreferences.getString("local_taikhoan", "");
    }


    public static void setMatKhau(String data) {
        sharedPreferences.edit().putString("local_matkhau", data).apply();
    }
    public static String getMatKhau() {
        return sharedPreferences.getString("local_matkhau", "");
    }


    public static void setName(String data) {
        sharedPreferences.edit().putString("local_name", data).apply();
    }
    public static String getName() {
        return sharedPreferences.getString("local_name", "");
    }


    public static void setRememberAcc(Boolean data) {
        sharedPreferences.edit().putBoolean("remember_acc", data).apply();
    }

    public static Boolean isRememberAcc() {
        return sharedPreferences.getBoolean("remember_acc", false);
    }

    public static void setRememberUserName(String data) {
        sharedPreferences.edit().putString("remember_user_name", data).apply();
    }

    public static String getRememberUserName() {
        return sharedPreferences.getString("remember_user_name", "");
    }

    public static void setRememberPassword(String data) {
        sharedPreferences.edit().putString("remember_password", data).apply();
    }

    public static String getRememberPassword() {
        return sharedPreferences.getString("remember_password", "");
    }


}
