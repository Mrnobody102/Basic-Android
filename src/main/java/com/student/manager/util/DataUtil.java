package com.student.manager.util;

import com.student.manager.model.CourseType;
import com.student.manager.model.Level;
import com.student.manager.model.SlotDay;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DataUtil {
    public static String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
        return  sdf.format(new Date());
    }
    public static ArrayList<Level> listLevel = new ArrayList<>();
    public static ArrayList<CourseType> listCourseType = new ArrayList<>();
    public static ArrayList<String> listSlotDay = new ArrayList<>();
    public static ArrayList<String> skills = new ArrayList<>();
    public static void init() {
        listLevel.clear();
        listLevel.add(new Level(1, "Beginner", ""));
        listLevel.add(new Level(2, "Normal", ""));
        listLevel.add(new Level(3, "Medium", ""));

        listCourseType.clear();
        listCourseType.add(new CourseType(1, "IELTS", ""));
        listCourseType.add(new CourseType(2, "TOEIC", ""));
        listCourseType.add(new CourseType(3, "English in use", ""));

        listSlotDay.clear();
        listSlotDay.add(Constant.SLOT_DAY_1);
        listSlotDay.add(Constant.SLOT_DAY_2);
        listSlotDay.add(Constant.SLOT_DAY_3);
        listSlotDay.add(Constant.SLOT_DAY_4);

        skills.clear();
        skills.add("Listening");
        skills.add("Speaking");
        skills.add("Reading");
        skills.add("Writing");

    }
}
