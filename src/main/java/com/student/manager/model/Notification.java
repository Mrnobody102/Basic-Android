package com.student.manager.model;

import android.os.Build;

import java.time.LocalDate;

public class Notification {
    private int notificationId;
    private String title;
    private String content;
    private int staffId;
    private int lecturerId;
    private int classId;
    private LocalDate date;

    public Notification(String title, String content, int staffId, int lecturerId, int classId, LocalDate date) {
        this.title = title;
        this.content = content;
        this.staffId = staffId;
        this.lecturerId = lecturerId;
        this.classId = classId;
        this.date = date;
    }


    public Notification(int notificationId, String title, String content, int staffId, int lecturerId,
                        int classId, String date) {
        this.notificationId = notificationId;
        this.title = title;
        this.content = content;
        this.staffId = staffId;
        this.lecturerId = lecturerId;
        this.classId = classId;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            this.date = LocalDate.parse(date);
        }
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public int getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(int lecturerId) {
        this.lecturerId = lecturerId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
