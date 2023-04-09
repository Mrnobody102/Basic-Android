package com.student.manager.dao;

import android.content.Context;
import android.database.Cursor;

import com.student.manager.database.AppDataBase;
import com.student.manager.model.Class;
import com.student.manager.model.Lecturer;
import com.student.manager.model.LecturerClassSkill;
import com.student.manager.util.Constant;

import java.util.ArrayList;
import java.util.List;

public class LecturerClassSkillDAO {
    public static AppDataBase dataBase;


    public static void init(Context context) {
        dataBase = new AppDataBase(context , Constant.NAME_DATABASE , null , 1);
        dataBase.QueryData(" CREATE TABLE IF NOT EXISTS LecturerClassSkill(" +
                "lecturer_id INTEGER, " +
                "class_id INTEGER, " +
                "skill_id INTEGER )" );
    }

    public static LecturerClassSkill getLecturerClassSkill(int lecturerId, int classId, int skillId) {
        String query = "SELECT * FROM LecturerClassSkill WHERE lecturer_id = " + lecturerId + " and skill_id = "+ skillId
                +" and class_id =  " + classId;
        Cursor data = dataBase.GetData(query);
        while (data.moveToNext()){
            return new LecturerClassSkill(
                    data.getInt(0),
                    data.getInt(1),
                    data.getInt(2)

            );
        }
        return null;
    }
    public static int getSkillByLecturerAndClass(int lecturerId, int classId) {
        String query = "SELECT * FROM LecturerClassSkill WHERE lecturer_id = " + lecturerId;
        Cursor data = dataBase.GetData(query);
        while (data.moveToNext()){
            return data.getInt(2);
        }
        return 0;
    }

    public static List<Integer> getListSkillByLecturerAndClass(int lecturerId, int classId) {
        List<Integer> lSkill = new ArrayList<>();
        String query = "SELECT * FROM LecturerClassSkill WHERE lecturer_id = " + lecturerId
                +" AND class_id = "+ classId;
        Cursor data = dataBase.GetData(query);
        while (data.moveToNext()){
            lSkill.add(new Integer(data.getInt(2)));
        }
        return lSkill;
    }

    public static Lecturer getLecturerByClassSkill(int skill_id, int class_id) {
        String query = "SELECT * FROM Lecturer, LecturerClassSkill" +
                "   WHERE LecturerClassSkill.lecturer_id = Lecturer.lecturer_id" + " AND  LecturerClassSkill.skill_id= "+ skill_id
                +" AND LecturerClassSkill.class_id =" + class_id;
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
                    data.getInt(8));
        }
        return null;
    }

    public static void insertLecturerClassSkill(LecturerClassSkill lecturerSkill) {
        String sql = String.format(
                "INSERT INTO LecturerClassSkill VALUES ( '%s', '%s', '%s' )",
                lecturerSkill.getLecturerId(),
                lecturerSkill.getClassId(),
                lecturerSkill.getSkillId()
        );
        dataBase.QueryData(sql);
    }

    public static void updateLecturer(LecturerClassSkill lecturerSkill) {
        String sql = String.format(
                "UPDATE LecturerClassSkill SET lecturer_id=%s WHERE skill_id = %s and class_id = %s",
                lecturerSkill.getLecturerId(),
                lecturerSkill.getSkillId(),
                lecturerSkill.getClassId()
        );
        dataBase.QueryData(sql);
    }

    public static ArrayList<Class> getListClassByLecturer(int id) {
        String query = "SELECT DISTINCT Classes.* FROM Classes JOIN LecturerClassSkill" +
                "   ON Classes.class_id = LecturerClassSkill.class_id" + " WHERE  LecturerClassSkill.lecturer_id= "+ id;
        Cursor data = dataBase.GetData(query);
        ArrayList<Class> list = new ArrayList<>();
        while (data.moveToNext()){
            list.add(new Class(
                    data.getInt(0),
                    data.getInt(1),
                    data.getInt(2),
                    data.getString(3),
                    data.getInt(4)
            ));
        }
        return list;
    }

    public static void deleteLecturerClassSkill(int lecturerId, int skillId) {
        dataBase.QueryData("DELETE FROM LecturerClassSkill WHERE lecturer_id = " + lecturerId +" and skill_id = "+ skillId  );
    }
}
