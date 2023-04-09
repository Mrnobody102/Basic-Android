package com.student.manager.dao;

import android.content.Context;
import android.database.Cursor;

import com.student.manager.database.AppDataBase;
import com.student.manager.model.Lecturer;
import com.student.manager.model.Staff;
import com.student.manager.util.Constant;

import java.util.ArrayList;

public class StaffDAO {

    public static AppDataBase dataBase;


    public static void init(Context context) {
        dataBase = new AppDataBase(context , Constant.NAME_DATABASE , null , 1);
        dataBase.QueryData(" CREATE TABLE IF NOT EXISTS Staff(" +
                "staff_id INTEGER PRIMARY KEY AUTOINCREMENT , " +
                "accountId INTEGER,  " +
                "name VARCHAR(1000),  " +
                "gender INTEGER,  " +
                "date_of_birth VARCHAR(1000),  " +
                "address VARCHAR(1000),    " +
                "phone_number VARCHAR(1000),    " +
                "date_of_join VARCHAR(1000),    " +
                "status INTEGER  " +
                ") ");

    }

    public static Staff getStaff(int accountId) {
        String query = "SELECT * FROM Staff WHERE accountId = " + accountId;
        Cursor data = dataBase.GetData(query);
        while (data.moveToNext()){
            return new Staff(
                    data.getInt(0),
                    data.getInt(1),
                    data.getString(2),
                    data.getInt(3),
                    data.getString(4),
                    data.getString(5),
                    data.getString(6),
                    data.getString(7),
                    data.getInt(8)
            );
        }
        return null;
    }

    public static Staff getStaffbyId(int accountId) {
        String query = "SELECT * FROM Staff WHERE staff_id = " + accountId;
        Cursor data = dataBase.GetData(query);
        while (data.moveToNext()){
            return new Staff(
                    data.getInt(0),
                    data.getInt(1),
                    data.getString(2),
                    data.getInt(3),
                    data.getString(4),
                    data.getString(5),
                    data.getString(6),
                    data.getString(7),
                    data.getInt(8)
            );
        }
        return null;
    }

    public static void insertStaff(Staff staff) {
        String sql = String.format(
                "INSERT INTO Staff VALUES ( null, %s,'%s', %s,'%s','%s','%s', '%s',%s)",
                staff.getAccountId(),
                staff.getName(),
                staff.getGender(),
                staff.getDate_of_birth(),
                staff.getAddress(),
                staff.getPhone_number(),
                staff.getDate_of_join(),
                staff.getStatus()
        );
        dataBase.QueryData(sql);
    }

    public static void updateStaff(Staff staff) {
        String sql = String.format(
                "UPDATE Staff SET name= '%s', gender= %s, date_of_birth= '%s'," +
                        " address= '%s', phone_number= '%s', date_of_join= '%s', " +
                        "status= %s WHERE staff_id = %s",
                staff.getName(),
                staff.getGender(),
                staff.getDate_of_birth(),
                staff.getAddress(),
                staff.getPhone_number(),
                staff.getDate_of_join(),
                staff.getStatus(),
                staff.getStaff_id()
        );
        dataBase.QueryData(sql);
    }

    public static void deleteStaff(int id) {
        dataBase.QueryData("DELETE FROM Staff WHERE staff_id = " + id +"" );
    }



    public static ArrayList<Staff> getListStaff() {
        String query = "SELECT * FROM Staff ";
        ArrayList<Staff> list = new ArrayList<Staff>();
        Cursor data = dataBase.GetData(query);
        while (data.moveToNext()){
            list.add(new Staff(
                    data.getInt(0),
                    data.getInt(1),
                    data.getString(2),
                    data.getInt(3),
                    data.getString(4),
                    data.getString(5),
                    data.getString(6),
                    data.getString(7),
                    data.getInt(8)
            ));
        }
        return list;
    }

}
