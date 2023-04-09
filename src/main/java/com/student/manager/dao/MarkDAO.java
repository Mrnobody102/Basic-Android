package com.student.manager.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.student.manager.database.AppDataBase;
import com.student.manager.model.Mark;
import com.student.manager.util.Constant;

import java.util.ArrayList;

public class MarkDAO {
    public static AppDataBase dataBase;

    public static void init(Context context) {
        dataBase = new AppDataBase(context , Constant.NAME_DATABASE , null , 1);
        dataBase.QueryData(" CREATE TABLE IF NOT EXISTS Mark(" +
                "mark_id INTEGER PRIMARY KEY AUTOINCREMENT , " +
                "student_id INTEGER , " +
                "class_id INTEGER , " +
                "lecturer_id INTEGER , " +
                "value FLOAT,  " +
                "skill INTEGER,  " +
                "create_date DATE, " +
                "modified_date DATE " +
                ") ");
    }

    public static int insertMark(Mark mark) {
        SQLiteDatabase db = dataBase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("student_id", mark.getStudentId());
        values.put("class_id", mark.getClassId());
        values.put("lecturer_id", mark.getLecturerId());
        values.put("value", mark.getValue());
        values.put("skill", mark.getSkill());
        values.put("create_date", mark.getCreatedDate());
        values.put("modified_date", mark.getModifiedDate());

        long newRowId = db.insert("Mark", null, values);
        db.close();
        return (int) newRowId;
    }

    public static void updateMark(Mark mark) {
        String sql = String.format(
                "UPDATE Mark SET value = %s, modified_date='%s' WHERE student_id" +
                        "= %s AND class_id = %s AND lecturer_id = %s AND skill = %s "
                ,
                mark.getValue(),
                mark.getModifiedDate(),
                mark.getStudentId(),
                mark.getClassId(),
                mark.getLecturerId(),
                mark.getSkill()
        );
        dataBase.QueryData(sql);
    }

    public static ArrayList<Mark> getListMarkByClass(int class_id, int skill) {
       String query = String.format(
                        "SELECT * FROM Mark WHERE class_id = %s AND skill = %s",
                        class_id,
                        skill
                );
        Cursor data = dataBase.GetData(query);
        ArrayList<Mark> list = new ArrayList<>();
        while (data.moveToNext()){
            list.add( new Mark(
                    data.getInt(0),
                    data.getInt(1),
                    data.getInt(2),
                    data.getInt(3),
                    data.getFloat(4),
                    data.getInt(5),
                    data.getString(6),
                    data.getString(7)
            ));
        }
        return list;
    }

    public static Mark getMark(int classId, int studentId, int skill) {
        String query = String.format(
                "SELECT * FROM Mark WHERE class_id = %s AND student_id = %s " +
                        " AND skill = %s",
                classId,
                studentId,
                skill
        );
        Cursor data = dataBase.GetData(query);
        ArrayList<Mark> list = new ArrayList<>();
        while (data.moveToNext()){
            return new Mark(
                    data.getInt(0),
                    data.getInt(1),
                    data.getInt(2),
                    data.getInt(3),
                    data.getFloat(4),
                    data.getInt(5),
                    data.getString(6),
                    data.getString(7)
            );
        }
        return null;
    }
    public static void deleteClassRoom(int id) {
        dataBase.QueryData(" DELETE FROM ClassRoom WHERE class_id = " + id +"" );
    }
}
