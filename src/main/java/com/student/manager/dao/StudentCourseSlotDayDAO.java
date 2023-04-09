package com.student.manager.dao;

import android.content.Context;
import android.database.Cursor;

import com.student.manager.database.AppDataBase;
import com.student.manager.model.CourseSlotDay;
import com.student.manager.model.Student;
import com.student.manager.model.StudentCourseSlotDay;
import com.student.manager.util.Constant;

import java.util.ArrayList;

public class StudentCourseSlotDayDAO {
    public static AppDataBase dataBase;


    public static void init(Context context) {
        dataBase = new AppDataBase(context , Constant.NAME_DATABASE , null , 1);
        dataBase.QueryData(" CREATE TABLE IF NOT EXISTS StudentCourseSlotDay(" +
                "student_id INTEGER, " +
                "course_id INTEGER, " +
                "slotDay_id INTEGER )" );
    }

    public static StudentCourseSlotDay getStudentCourseSlotDay(int studentId, int courseId, int slotDayId) {
        String query = "SELECT * FROM StudentCourseSlotDay WHERE slotDay_id = " + studentId + " and slotDay_id = "+ slotDayId
                +" and course_id =  " + courseId;
        Cursor data = dataBase.GetData(query);
        while (data.moveToNext()){
            return new StudentCourseSlotDay(
                    data.getInt(0),
                    data.getInt(1),
                    data.getInt(2)

            );
        }
        return null;
    }

    public static ArrayList<Student> getListStudentByCourseSlotDay(int slotDay_id, int course_id) {
        String query = "SELECT * FROM Student, StudentCourseSlotDay" +
                "   WHERE StudentCourseSlotDay.student_id = Student.student_id" + " AND  StudentCourseSlotDay.slotDay_id= "+ slotDay_id
                +" AND StudentCourseSlotDay.course_id =" + course_id;
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

    public static void insertStudentCourseSlotDay(StudentCourseSlotDay studentSlotDay) {
        String sql = String.format(
                "INSERT INTO StudentCourseSlotDay VALUES ( '%s', '%s', '%s' )",
                studentSlotDay.getStudentId(),
                studentSlotDay.getCourseId(),
                studentSlotDay.getSlotDayId()
        );
        dataBase.QueryData(sql);
    }

    public static void updateStudentCourseSlotDay(StudentCourseSlotDay studentSlotDay , int oldCourseId) {
        String sql = String.format(
                "UPDATE StudentCourseSlotDay SET course_id=%s, slotDay_id=%s WHERE student_id = %s and course_id = %s",
                studentSlotDay.getStudentId(),
                studentSlotDay.getCourseId(),
                studentSlotDay.getSlotDayId(),
                studentSlotDay.getStudentId(),
                oldCourseId
        );
        dataBase.QueryData(sql);
    }

    public static void deleteStudentCourseSlotDay(int studentId, int slotDayId) {
        dataBase.QueryData("DELETE FROM StudentCourseSlotDay WHERE student_id = " + studentId +" and slotDay_id = "+ slotDayId  );
    }
}
