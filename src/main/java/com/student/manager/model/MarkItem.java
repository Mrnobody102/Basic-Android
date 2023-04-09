package com.student.manager.model;

public class MarkItem {
    int stt;
    String studentName;
    double point;

    public MarkItem() {
    }

    public MarkItem(int stt, String studentName, double point) {
        this.stt = stt;
        this.studentName = studentName;
        this.point = point;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(float point) {
        this.point = point;
    }
}
