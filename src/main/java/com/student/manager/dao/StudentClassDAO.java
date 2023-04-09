package com.student.manager.dao;

import android.content.Context;
import android.database.Cursor;

import com.student.manager.database.AppDataBase;
import com.student.manager.model.Class;
import com.student.manager.model.Student;
import com.student.manager.model.StudentClass;
import com.student.manager.util.Constant;

import java.util.ArrayList;

public class StudentClassDAO {
    public static AppDataBase dataBase;


    public static void init(Context context) {
        dataBase = new AppDataBase(context , Constant.NAME_DATABASE , null , 1);
        dataBase.QueryData(" CREATE TABLE IF NOT EXISTS StudentClass(" +
                "student_id INTEGER, " +
                "class_id INTEGER )" );
    }

    public static StudentClass getStudentClass(int studentId, int classId, int status) {
        String query = "SELECT * FROM StudentClass WHERE class_id = " + studentId + " and class_id = "+ classId;
        Cursor data = dataBase.GetData(query);
        while (data.moveToNext()){
            return new StudentClass(
                    data.getInt(0),
                    data.getInt(1)
            );
        }
        return null;
    }

    public static ArrayList<Student> getListStudentByClass(int class_id) {
        String query = "SELECT * FROM Student JOIN StudentClass" +
                "   ON StudentClass.student_id = Student.student_id" + " WHERE  StudentClass.class_id= "+ class_id
                ;
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

    public static ArrayList<Class> getListClassByStudent(int id) {
        String query = "SELECT * FROM Classes, StudentClass" +
                "   WHERE StudentClass.class_id = Classes.class_id" + " AND  StudentClass.student_id= "+ id;
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

    public static void insertStudentClass(StudentClass studentClass) {
        String sql = String.format(
                "INSERT INTO StudentClass VALUES ( '%s', '%s' )",
                studentClass.getStudentId(),
                studentClass.getClassId()
        );
        dataBase.QueryData(sql);
    }

    public static void updateStudent(StudentClass studentClass) {
        String sql = String.format(
                "UPDATE StudentClass SET student_id=%s , class_id=%s WHERE student_id = %s and class_id = %s",
                studentClass.getStudentId(),
                studentClass.getClassId(),
                studentClass.getStudentId(),
                studentClass.getClassId()
        );
        dataBase.QueryData(sql);
    }

    public static void deleteStudentClass(int studentId, int classId) {
        dataBase.QueryData("DELETE FROM StudentClass WHERE student_id = " + studentId +" and class_id = "+ classId  );
    }
}
