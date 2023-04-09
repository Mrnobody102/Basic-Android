package com.student.manager.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.student.manager.database.AppDataBase;
import com.student.manager.model.Account;
import com.student.manager.model.Student;
import com.student.manager.model.Student;
import com.student.manager.util.Constant;

import java.util.ArrayList;

public class StudentDAO {

    public static AppDataBase dataBase;


    public static void init(Context context) {
        dataBase = new AppDataBase(context , Constant.NAME_DATABASE , null , 1);
        dataBase.QueryData(" CREATE TABLE IF NOT EXISTS Student(" +
                "student_id INTEGER PRIMARY KEY AUTOINCREMENT , " +
                "accountId INTEGER,  " +
                "name VARCHAR(1000),  " +
                "gender INTEGER,  " +
                "date_of_birth VARCHAR(1000),  " +
                "address VARCHAR(1000),    " +
                "phone_number VARCHAR(1000),    " +
                "start_level INTEGER,  " +
                "date_of_join VARCHAR(1000),    " +
                "status INTEGER  " +
                ") ");

    }

    public static Student getStudent(int accountId) {
        String query = "SELECT * FROM Student WHERE accountId = " + accountId;
        Cursor data = dataBase.GetData(query);
        while (data.moveToNext()){
            return new Student(
                    data.getInt(0),
                    data.getInt(1),
                    data.getString(2),
                    data.getInt(3),
                    data.getString(4),
                    data.getString(5),
                    data.getString(6),
                    data.getInt(7),
                    data.getString(8),
                    data.getInt(9)
                    );
        }
        return null;
    }

    public static Student getStudentById(int accountId) {
        String query = "SELECT * FROM Student WHERE student_id = " + accountId;
        Cursor data = dataBase.GetData(query);
        while (data.moveToNext()){
            return new Student(
                    data.getInt(0),
                    data.getInt(1),
                    data.getString(2),
                    data.getInt(3),
                    data.getString(4),
                    data.getString(5),
                    data.getString(6),
                    data.getInt(7),
                    data.getString(8),
                    data.getInt(9)
            );
        }
        return null;
    }

    public static ArrayList<Student> getListStudent() {
        String query = "SELECT * FROM Student" ;
        Cursor data = dataBase.GetData(query);
        ArrayList<Student> list = new ArrayList<>();
        while (data.moveToNext()){
            list.add(new Student(
                    data.getInt(0),
                    data.getInt(1),
                    data.getString(2),
                    data.getInt(3),
                    data.getString(4),
                    data.getString(5),
                    data.getString(6),
                    data.getInt(7),
                    data.getString(8),
                    data.getInt(9)
                    ));
        }
        return list;
    }

    public static int insertStudent(Student student) {
        SQLiteDatabase db = dataBase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("accountId", student.getAccountId());
        values.put("name", student.getName());
        values.put("gender", student.getGender());
        values.put("date_of_birth", student.getDate_of_birth());
        values.put("address", student.getAddress());
        values.put("phone_number", student.getPhone_number());
        values.put("start_level", student.getStart_level());
        values.put("date_of_join", student.getDate_of_join());
        values.put("status", student.getStatus());
        long newRowId = db.insert("Student", null, values);
        db.close();
        return (int) newRowId;
    }



    public static void updateStudent(Student student) {
        String sql = String.format(
                "UPDATE Student SET name= '%s', gender= %s, date_of_birth= '%s', " +
                        "address= '%s', phone_number= '%s', start_level= %s, date_of_join= '%s', " +
                        "status= %s WHERE student_id = %s",
                student.getName(),
                student.getGender(),
                student.getDate_of_birth(),
                student.getAddress(),
                student.getPhone_number(),
                student.getStart_level(),
                student.getDate_of_join(),
                student.getStatus(),
                student.getStudent_id()
        );
        dataBase.QueryData(sql);
    }

    public static void deleteStudent(int id) {
        dataBase.QueryData("DELETE FROM Account WHERE accountId = " + id +"" );
    }

}
