package com.student.manager.model;

public class StudentCourseSlotDay {
    private int studentId;
    private int courseId;
    private int slotDayId;

    public StudentCourseSlotDay(int studentId, int courseId, int slotDayId) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.slotDayId = slotDayId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getSlotDayId() {
        return slotDayId;
    }

    public void setSlotDayId(int slotDayId) {
        this.slotDayId = slotDayId;
    }
}
