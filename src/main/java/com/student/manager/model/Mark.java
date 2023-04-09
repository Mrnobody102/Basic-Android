package com.student.manager.model;

public class Mark {
    private int markId;
    private int studentId;
    private int classId;
    private int lecturerId;
    private double value;
    private int skill;
    private String createdDate;
    private String modifiedDate;

    public Mark(int studentId, int classId, int lecturerId, double value, int skill, String createdDate, String modifiedDate) {
        this.studentId = studentId;
        this.classId = classId;
        this.lecturerId = lecturerId;
        this.value = value;
        this.skill = skill;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public Mark(int markId, int studentId, int classId, int lecturerId, double value, int skill, String createdDate, String modifiedDate) {
        this.markId = markId;
        this.studentId = studentId;
        this.classId = classId;
        this.lecturerId = lecturerId;
        this.value = value;
        this.skill = skill;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public Mark() {
    }

    public int getMarkId() {
        return markId;
    }

    public void setMarkId(int markId) {
        this.markId = markId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(int lecturerId) {
        this.lecturerId = lecturerId;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }
}
