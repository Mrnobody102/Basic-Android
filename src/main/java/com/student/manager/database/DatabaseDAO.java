package com.student.manager.database;

import android.content.Context;
import android.database.Cursor;

import com.student.manager.util.Constant;

public class DatabaseDAO {

    public static AppDataBase dataBase;

    public static void init(Context context){
        dataBase = new AppDataBase(context , Constant.NAME_DATABASE , null , 1);
    }

    public static boolean checkPass(Context context, String tk, String mk) {
        Cursor data = dataBase.GetData("SELECT COUNT(*) FROM TaiKhoan WHERE taikhoan ='"+tk+"' AND matkhau='"+mk+"'");
        int count = 0;
        while (data.moveToNext()){
            count = data.getInt(0);
        }
        return count != 0;
    }

    public static void saveTaiKhoan(Context context, String tkk) {
        Cursor data = dataBase.GetData("SELECT * FROM TaiKhoan WHERE taikhoan ='"+tkk+"'");
        while (data.moveToNext()){
            int id = data.getInt(0);
            String tk = data.getString(1);
            String mk = data.getString(2);
            String name = data.getString(3);
        }
    }

    public static boolean checkExit(Context context, String tk) {
        Cursor data = dataBase.GetData("SELECT COUNT(*) FROM TaiKhoan WHERE taikhoan ='"+tk+"'");
        int count = 0;
        while (data.moveToNext()){
            count = data.getInt(0);
        }
        return count != 0;
    }

    public static void addTaiKhoan(Context context, String tk, String mk, String name) {
        dataBase.QueryData("INSERT INTO TaiKhoan VALUES ( null , '"+tk+"', '"+mk+"', '"+name+"')");
    }
}
