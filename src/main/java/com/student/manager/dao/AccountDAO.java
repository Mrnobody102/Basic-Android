package com.student.manager.dao;

import android.content.Context;
import android.database.Cursor;

import com.student.manager.database.AppDataBase;
import com.student.manager.model.Account;
import com.student.manager.model.Student;
import com.student.manager.util.Constant;

import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    public static AppDataBase dataBase;

    public static void init(Context context) {
        dataBase = new AppDataBase(context , Constant.NAME_DATABASE , null , 1);
        dataBase.QueryData(" CREATE TABLE IF NOT EXISTS Account(" +
                "accountId INTEGER PRIMARY KEY AUTOINCREMENT , " +
                "user_name VARCHAR(1000),  " +
                "password VARCHAR(1000),  " +
                "role VARCHAR(1000),    " +
                "email VARCHAR(1000)    " +
                ") ");

    }

    public static Account login(String username, String password) {
        String query = "SELECT * FROM Account WHERE user_name = '"+username+"' AND password= '"+password+"'";
        Cursor data = dataBase.GetData(query);
        while (data.moveToNext()){
            return new Account(
                    data.getInt(0),
                    data.getString(1),
                    data.getString(2),
                    data.getString(3),
                    data.getString(4)
                    );
        }
        return null;
    }

    public static Account checkExit(String username) {
        String query = "SELECT * FROM Account WHERE user_name = '"+username+"'";
        Cursor data = dataBase.GetData(query);
        while (data.moveToNext()){
            return new Account(
                    data.getInt(0),
                    data.getString(1),
                    data.getString(2),
                    data.getString(3),
                    data.getString(4)
                    );
        }
        return null;
    }

    public static void insertAccount(Account account) {
        String sql = String.format(
                "INSERT INTO Account VALUES ( null, '%s', '%s','%s','%s')",
                account.getUser_name(),
                account.getPassword(),
                account.getRole(),
                account.getEmail()
        );
        dataBase.QueryData(sql);
    }

    public static void deleteAccount(int id) {
        dataBase.QueryData(" DELETE FROM Account WHERE accountId = " + id +"" );
    }

}
