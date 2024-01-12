package com.example.kukufarm;

public class User {
    String username,email,idNo,location,password;

    public User() {
    }

    public User(String username, String idNo, String email, String location, String password) {
        this.username=username;
        this.email = email;
        this.idNo = idNo;
        this.location=location;
        this.password=password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getIdNo() {
        return idNo;
    }

    public String getLocation() {
        return location;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
