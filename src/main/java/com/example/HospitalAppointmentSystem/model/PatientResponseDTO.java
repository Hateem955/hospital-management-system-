package com.example.HospitalAppointmentSystem.model;

public class PatientResponseDTO {

    private int id;
    private String name;
    private String email;
    private int age;
    private String username;

    public PatientResponseDTO(int id, String name, String email, int age, String username) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
