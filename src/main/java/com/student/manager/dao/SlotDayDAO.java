package com.student.manager.dao;

import android.content.Context;
import android.database.Cursor;

import com.student.manager.database.AppDataBase;
import com.student.manager.model.Course;
import com.student.manager.model.SlotDay;
import com.student.manager.util.Constant;

import java.util.ArrayList;

public class SlotDayDAO {
    public static AppDataBase dataBase;

    public static void init(Context context) {
        dataBase = new AppDataBase(context , Constant.NAME_DATABASE , null , 1);
        dataBase.QueryData(" CREATE TABLE IF NOT EXISTS SlotDay(" +
                "slot_day_id INTEGER PRIMARY KEY AUTOINCREMENT , " +
                "name VARCHAR(200)  " +
                ") ");

    }

    public static void insertSlotDay(SlotDay slotDay) {
        String sql = String.format(
                "INSERT INTO SlotDay VALUES ( null, '%s') \n",
                slotDay.getSlotDay()
                );
        dataBase.QueryData(sql);
    }

    public static ArrayList<SlotDay> getListSlotDay() {
        String query = "SELECT * FROM SlotDay ";
        Cursor data = dataBase.GetData(query);
        ArrayList<SlotDay> list = new ArrayList<>();
        while (data.moveToNext()){
            list.add( new SlotDay(
                    data.getInt(0),
                    data.getString(1)
            ));
        }
        return list;
    }
}
