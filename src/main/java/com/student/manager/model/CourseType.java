package com.student.manager.model;

public class CourseType {
    int course_type_id;
    String name;
    String description;

    public CourseType() {
    }

    public CourseType(int course_type_id, String name, String description) {
        this.course_type_id = course_type_id;
        this.name = name;
        this.description = description;
    }

    public int getCourse_type_id() {
        return course_type_id;
    }

    public void setCourse_type_id(int course_type_id) {
        this.course_type_id = course_type_id;
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

    @Override
    public String toString() {
        return name;
    }
}
