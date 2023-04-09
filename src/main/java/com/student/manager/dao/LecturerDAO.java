package com.student.manager.dao;

import android.content.Context;
import android.database.Cursor;

import com.student.manager.database.AppDataBase;
import com.student.manager.model.Lecturer;
import com.student.manager.model.Student;
import com.student.manager.util.Constant;

import java.util.ArrayList;

public class LecturerDAO {
    public static AppDataBase dataBase;


    public static void init(Context context) {
        dataBase = new AppDataBase(context , Constant.NAME_DATABASE , null , 1);
        dataBase.QueryData(" CREATE TABLE IF NOT EXISTS Lecturer(" +
                "lecturer_id INTEGER PRIMARY KEY AUTOINCREMENT , " +
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

    public static Lecturer getLecturer(int accountId) {
        String query = "SELECT * FROM Lecturer WHERE accountId = " + accountId;
        Cursor data = dataBase.GetData(query);
        while (data.moveToNext()){
            return new Lecturer(
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

    public static Lecturer getLecturerById(int lecturerId) {
        String query = "SELECT * FROM Lecturer WHERE lecturer_id = " + lecturerId;
        Cursor data = dataBase.GetData(query);
        while (data.moveToNext()){
            return new Lecturer(
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

    public static void insertLecturer(Lecturer lecturer) {
        String sql = String.format(
                "INSERT INTO Lecturer VALUES ( null, %s,'%s', %s,'%s','%s','%s', '%s',%s)",
                lecturer.getAccountId(),
                lecturer.getName(),
                lecturer.getGender(),
                lecturer.getDate_of_birth(),
                lecturer.getAddress(),
                lecturer.getPhone_number(),
                lecturer.getDate_of_join(),
                lecturer.getStatus()
        );
        dataBase.QueryData(sql);
    }

    public static void updateLecturer(Lecturer lecturer) {
        String sql = String.format(
                "UPDATE Lecturer SET name= '%s', gender= %s, date_of_birth= '%s', address= '%s'," +
                        " phone_number= '%s', date_of_join= '%s', status= %s WHERE lecturer_id = %s",
                lecturer.getName(),
                lecturer.getGender(),
                lecturer.getDate_of_birth(),
                lecturer.getAddress(),
                lecturer.getPhone_number(),
                lecturer.getDate_of_join(),
                lecturer.getStatus(),
                lecturer.getLecturer_id()
        );
        dataBase.QueryData(sql);
    }

    public static void deleteLecturer(int id) {
        dataBase.QueryData("DELETE FROM Lecturer WHERE lecturer_id = " + id +"" );
    }



    public static ArrayList<Lecturer> getListLecturer() {
        String query = "SELECT * FROM Lecturer";
        ArrayList<Lecturer> list = new ArrayList<Lecturer>();
        Cursor data = dataBase.GetData(query);
        while (data.moveToNext()){
            list.add(new Lecturer(
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
