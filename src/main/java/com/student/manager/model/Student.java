package com.student.manager.model;

import java.util.Date;
import java.util.List;

public class Student {
    int student_id;
    int accountId;
    String name;
    int gender;
    String date_of_birth;
    String address;
    String phone_number;
    int start_level;
    String date_of_join;

    int status;

    public Student(int student_id, int accountId, String name, int gender, String date_of_birth, String address, String phone_number, int start_level, String date_of_join, int status) {
        this.student_id = student_id;
        this.accountId = accountId;
        this.name = name;
        this.gender = gender;
        this.date_of_birth = date_of_birth;
        this.address = address;
        this.phone_number = phone_number;
        this.start_level = start_level;
        this.date_of_join = date_of_join;
        this.status = status;
    }

    public Student() {

    }

    public Student(int accountId, String name, int gender, String date_of_birth, String address, String phone_number, int start_level, String date_of_join, int status) {
        this.accountId = accountId;
        this.name = name;
        this.gender = gender;
        this.date_of_birth = date_of_birth;
        this.address = address;
        this.phone_number = phone_number;
        this.start_level = start_level;
        this.date_of_join = date_of_join;
        this.status = status;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public int getStart_level() {
        return start_level;
    }

    public void setStart_level(int start_level) {
        this.start_level = start_level;
    }

    public String getDate_of_join() {
        return date_of_join;
    }

    public void setDate_of_join(String date_of_join) {
        this.date_of_join = date_of_join;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
