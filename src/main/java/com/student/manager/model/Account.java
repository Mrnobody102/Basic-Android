package com.student.manager.model;

public class Account {
    int accountId;
    String user_name;
    String password;
    String role;
    String email;

    public Account(String user_name, String password, String role, String email) {
        this.user_name = user_name;
        this.password = password;
        this.role = role;
        this.email = email;
    }

    public Account(int accountId, String user_name, String password, String role, String email) {
        this.accountId = accountId;
        this.user_name = user_name;
        this.password = password;
        this.role = role;
        this.email = email;
    }

    public Account() {

    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
