package com.student.manager.dao;

import android.content.Context;
import android.database.Cursor;

import com.student.manager.database.AppDataBase;
import com.student.manager.model.Notification;
import com.student.manager.util.Constant;

import java.util.ArrayList;

public class NotificationDAO {
    public static AppDataBase dataBase;

    public static void init(Context context) {
        dataBase = new AppDataBase(context , Constant.NAME_DATABASE , null , 1);
        dataBase.QueryData(" CREATE TABLE IF NOT EXISTS Notification(" +
                "notification_id INTEGER PRIMARY KEY AUTOINCREMENT , " +
                "title VARCHAR(1000),  " +
                "content VARCHAR(1000),  " +
                "staff_id INTEGER,    " +
                "lecturer_id INTEGER,    " +
                "class_id INTEGER,    " +
                "date DATE    " +
                ") ");

    }

    public static Notification getNotificationById(int notificationId) {
        String query = "SELECT * FROM Notification WHERE notification_id = " + notificationId;
        Cursor data = dataBase.GetData(query);
        while (data.moveToNext()){
            Notification notification = new Notification(data.getInt(0),
                    data.getString(1),
                    data.getString(2),
                    data.getInt(3),
                    data.getInt(4),
                    data.getInt(5),
                    data.getString(6));
            return notification;
        }
        return null;
    }


    public static void insertNotification(Notification notification) {
        String sql = String.format(
                "INSERT INTO Notification VALUES ( null, '%s', '%s', %s , %s , %s , '%s')",
                notification.getTitle(),
                notification.getContent(),
                notification.getStaffId(),
                notification.getLecturerId(),
                notification.getClassId(),
                notification.getDate()+""
        );
        dataBase.QueryData(sql);
    }
    public static void updateNotification(Notification notification) {
        String sql = String.format(
                "UPDATE Notification SET title= '%s', content= '%s' WHERE notification_id = %s",
                notification.getTitle(),
                notification.getContent(),
                notification.getNotificationId()
        );
        dataBase.QueryData(sql);
    }

    public static void deleteNotification(int id) {
        dataBase.QueryData(" DELETE FROM Notification WHERE notification_id = " + id +"" );
    }

    public static ArrayList<Notification> getListNotificationByStaff() {
        String query = "SELECT * FROM Notification where staff_id = 1 order by date desc, " +
                "notification_id DESC";
        Cursor data = dataBase.GetData(query);
        ArrayList<Notification> list = new ArrayList<>();
        while (data.moveToNext()){
            list.add( new Notification(data.getInt(0),
                    data.getString(1),
                    data.getString(2),
                    data.getInt(3),
                    data.getInt(4),
                    data.getInt(5),
                    data.getString(6))
            );
        }
        return list;
    }
    public static ArrayList<Notification> getListNotificationByClass(int classId) {
        String query = "SELECT * FROM Notification where class_id = "+ classId
                +" order by date desc, notification_id desc";
        Cursor data = dataBase.GetData(query);
        ArrayList<Notification> list = new ArrayList<>();
        while (data.moveToNext()){
            list.add( new Notification(data.getInt(0),
                    data.getString(1),
                    data.getString(2),
                    data.getInt(3),
                    data.getInt(4),
                    data.getInt(5),
                    data.getString(6))
            );
        }
        return list;
    }
}
