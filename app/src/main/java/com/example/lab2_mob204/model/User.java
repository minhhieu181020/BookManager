package com.example.lab2_mob204.model;

public class User {
    private String username;
    private String pass;
    private String phone;
    private String fullname;

    public User() {

    }
    public User(String username, String pass, String phone, String fullname) {
        this.username = username;
        this.pass = pass;
        this.phone = phone;
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }


}
