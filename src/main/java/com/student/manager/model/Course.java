package com.student.manager.model;

import java.util.Date;

public class Course {
    int course_id;
    String name;
    String description;
    int start_level;
    int course_type_id;
    Date start_date;
    Date end_date;

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Course(int course_id, String name, String description, int start_level, int course_type_id) {
        this.course_id = course_id;
        this.name = name;
        this.description = description;
        this.start_level = start_level;
        this.course_type_id = course_type_id;
    }

    public Course() {
    }

    public Course(String name, String description, int start_level, int course_type_id) {
        this.name = name;
        this.description = description;
        this.start_level = start_level;
        this.course_type_id = course_type_id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStart_level() {
        return start_level;
    }

    public void setStart_level(int start_level) {
        this.start_level = start_level;
    }

    public int getCourse_type_id() {
        return course_type_id;
    }

    public void setCourse_type_id(int course_type_id) {
        this.course_type_id = course_type_id;
    }

    @Override
    public String toString() {
        return name;
    }
}
