package com.example.HospitalAppointmentSystem.model;

import jakarta.persistence.*;

@Entity
public class Patient {

    @Id
    @GeneratedValue
    private int patient_id;
    private String name;
    private String email;
    private int age;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private AppUser appUser;

    public int getPatient_id() {
        return patient_id;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }



    @Override
    public String toString() {
        return "Patient{" +
                "patient_id=" + patient_id +
                ", name='" + name + '\'' +
                ", email_address='" + email + '\'' +
                ", age=" + age +
                ", appUser=" + appUser +
                '}';
    }
}
