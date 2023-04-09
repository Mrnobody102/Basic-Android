package com.student.manager.model;

import java.util.List;

public class Class {
    int class_id;
    int course_id;
    int slot_day_id;
    String name;
    // 0: not have lecturer, 1: not start, 2: processing, 3 : finished 4:stop
    int status;

    List<Student> students;
    List<Lecturer> lecturers;

    public Class(int course_id, int slotday_id, String name, int status, List<Student> students, List<Lecturer> lecturers) {
        this.course_id = course_id;
        this.slot_day_id = slotday_id;
        this.name = name;
        this.status = status;
        this.students = students;
        this.lecturers = lecturers;
    }

    public Class(int class_id, int course_id, int slotday_id, String name, int status) {
        this.class_id = class_id;
        this.course_id = course_id;
        this.slot_day_id = slotday_id;
        this.name = name;
        this.status = status;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Lecturer> getLecturers() {
        return lecturers;
    }

    public void setLecturers(List<Lecturer> lecturers) {
        this.lecturers = lecturers;
    }

    public Class() {
    }


    public int getSlot_day_id() {
        return slot_day_id;
    }

    public void setSlot_day_id(int slot_day_id) {
        this.slot_day_id = slot_day_id;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
