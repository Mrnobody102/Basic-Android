package com.student.manager.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.student.manager.database.AppDataBase;
import com.student.manager.model.Class;
import com.student.manager.util.Constant;

import java.util.ArrayList;

public class ClassDAO {
    public static AppDataBase dataBase;

    public static void init(Context context) {
        dataBase = new AppDataBase(context , Constant.NAME_DATABASE , null , 1);
        dataBase.QueryData(" CREATE TABLE IF NOT EXISTS Classes(" +
                "class_id INTEGER PRIMARY KEY AUTOINCREMENT , " +
                "course_id INTEGER , " +
                "slot_day_id INTEGER , " +
                "name VARCHAR(1000),  " +
                "status INTEGER " +
                ") ");
    }

    public static int insertClassRoom(Class aClass) {
        SQLiteDatabase db = dataBase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("course_id", aClass.getCourse_id());
        values.put("slot_day_id", aClass.getSlot_day_id());
        values.put("name", aClass.getName());
        values.put("status", aClass.getStatus());
        long newRowId = db.insert("Classes", null, values);
        db.close();
        return (int) newRowId;
    }

    public static void updateClassRoom(Class aClass) {
        String sql = String.format(
                "UPDATE Classes SET course_id = %s, name='%s', status= %s WHERE class_id = %s",
                aClass.getCourse_id(),
                aClass.getName(),
                aClass.getStatus(),
                aClass.getClass_id()
        );
        dataBase.QueryData(sql);
    }

    public static ArrayList<Class> getListClassRoom() {
        String query = "SELECT * FROM Classes ";
        Cursor data = dataBase.GetData(query);
        ArrayList<Class> list = new ArrayList<>();
        while (data.moveToNext()){
            list.add( new Class(
                    data.getInt(0),
                    data.getInt(1),
                    data.getInt(2),
                    data.getString(3),
                    data.getInt(4)
            ));
        }
        return list;
    }

    public static Class getClassById(int id) {
        String query = "SELECT * FROM Classes WHERE class_id = " + id;
        Cursor data = dataBase.GetData(query);
        while (data.moveToNext()){
            return new Class(
                    data.getInt(0),
                    data.getInt(1),
                    data.getInt(2),
                    data.getString(3),
                    data.getInt(4)
            );
        }
        return null;
    }

    public static Class getClassByCourseIdAndSlotDayId(int cid, int sid) {
        String query = "SELECT * FROM Classes WHERE course_id = " + cid + " AND slot_day_id = " + sid;
        Cursor data = dataBase.GetData(query);
        while (data.moveToNext()){
            return new Class(
                    data.getInt(0),
                    data.getInt(1),
                    data.getInt(2),
                    data.getString(3),
                    data.getInt(4)
            );
        }
        return null;
    }



    public static void deleteClassRoom(int id) {
        dataBase.QueryData(" DELETE FROM ClassRoom WHERE class_id = " + id +"" );
    }
}
