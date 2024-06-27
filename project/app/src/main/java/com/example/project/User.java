package com.example.project;

import android.widget.EditText;

public class User {
    private String username;
    private String password;
    private String email;
    private String name;
    private String city;
    private String region;

    public User(EditText username, EditText password, EditText email, EditText name, EditText city, EditText region, EditText type) {
        this.username = String.valueOf(username);
        this.password = String.valueOf(password);
        this.email = String.valueOf(email);
        this.name = String.valueOf(name);
        this.city = String.valueOf(city);
        this.region = String.valueOf(region);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
