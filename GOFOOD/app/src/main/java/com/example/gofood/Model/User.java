package com.example.gofood.Model;

public class User {

    int id;
    String phone,fullname,addreas;

    public User(int id, String phone, String fullname, String addreas) {
        this.id = id;
        this.phone = phone;
        this.fullname = fullname;
        this.addreas = addreas;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getAddreas() {
        return addreas;
    }

    public void setAddreas(String addreas) {
        this.addreas = addreas;
    }

}