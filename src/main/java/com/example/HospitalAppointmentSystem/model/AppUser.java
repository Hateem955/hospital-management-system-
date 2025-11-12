package com.example.HospitalAppointmentSystem.model;

import jakarta.persistence.*;

@Entity
public class AppUser {

    @Id
    @GeneratedValue
    private int user_id;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Roles role;


    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }



    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
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
}
