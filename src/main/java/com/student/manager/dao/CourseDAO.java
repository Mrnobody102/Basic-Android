package com.student.manager.dao;

import android.content.Context;
import android.database.Cursor;

import com.student.manager.database.AppDataBase;
import com.student.manager.model.Course;
import com.student.manager.util.Constant;

import java.util.ArrayList;

public class CourseDAO {

    public static AppDataBase dataBase;

    public static void init(Context context) {
        dataBase = new AppDataBase(context , Constant.NAME_DATABASE , null , 1);
        dataBase.QueryData(" CREATE TABLE IF NOT EXISTS Course(" +
                "course_id INTEGER PRIMARY KEY AUTOINCREMENT , " +
                "name VARCHAR(1000),  " +
                "description VARCHAR(1000),  " +
                "start_level INTEGER,    " +
                "course_type_id INTEGER    " +
                ") ");

    }

    public static void insertCourse(Course course) {
        String sql = String.format(
                "INSERT INTO Course VALUES ( null, '%s', '%s', %s, %s)",
                course.getName(),
                course.getDescription(),
                course.getStart_level(),
                course.getCourse_type_id()
        );
        dataBase.QueryData(sql);
    }

    public static void updateCourse(Course course) {
        String sql = String.format(
                "UPDATE Course SET name= '%s', description= '%s', start_level= %s, course_type_id = %s WHERE course_id = %s",
                course.getName(),
                course.getDescription(),
                course.getStart_level(),
                course.getCourse_type_id(),
                course.getCourse_id()
        );
        dataBase.QueryData(sql);
    }

    public static ArrayList<Course> getListCourse() {
        String query = "SELECT * FROM Course ";
        Cursor data = dataBase.GetData(query);
        ArrayList<Course> list = new ArrayList<>();
        while (data.moveToNext()){
            list.add( new Course(
                    data.getInt(0),
                    data.getString(1),
                    data.getString(2),
                    data.getInt(3),
                    data.getInt(4)
            ));
        }
        return list;
    }

    public static Course getCourseById(int id) {
        String query = "SELECT * FROM Course WHERE course_id="+id;
        Cursor data = dataBase.GetData(query);
        while (data.moveToNext()){
            return new Course(
                    data.getInt(0),
                    data.getString(1),
                    data.getString(2),
                    data.getInt(3),
                    data.getInt(4)
            );
        }
        return null;
    }

    public static ArrayList<Course> getCoursesByStudentId(int id) {
        String query = "SELECT * FROM Course c, StudentCourseSlotDay s WHERE c.course_id=s.course_id AND s.student_id="+id;
        Cursor data = dataBase.GetData(query);
        ArrayList<Course> list = new ArrayList<>();
        while (data.moveToNext()){
            list.add( new Course(
                    data.getInt(0),
                    data.getString(1),
                    data.getString(2),
                    data.getInt(3),
                    data.getInt(4)
            ));
        }
        return list;
    }

    public static void deleteCourse(int id) {
        dataBase.QueryData(" DELETE FROM Course WHERE course_id = " + id +"" );
    }
}
