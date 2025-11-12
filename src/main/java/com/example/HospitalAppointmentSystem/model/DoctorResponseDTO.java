package com.example.HospitalAppointmentSystem.model;

import java.util.List;

public class DoctorResponseDTO {

    private int doc_id;
    private String name;
    private String specialization;
    private int yearsOfExperience;
    private String username;
    private List<TimeSlotDTO> timeSlots; // <-- new field

    public List<TimeSlotDTO> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(List<TimeSlotDTO> timeSlots) {
        this.timeSlots = timeSlots;
    }

    public DoctorResponseDTO(int doc_id, String name, String specialization, int yearsOfExperience, String username) {
        this.doc_id = doc_id;
        this.name = name;
        this.specialization = specialization;
        this.yearsOfExperience = yearsOfExperience;
        this.username = username;
    }

    public DoctorResponseDTO(int doc_id, String name, String specialization, int yearsOfExperience, String username, List<TimeSlotDTO> timeSlots) {
        this.doc_id = doc_id;
        this.name = name;
        this.specialization = specialization;
        this.yearsOfExperience = yearsOfExperience;
        this.username = username;
        this.timeSlots = timeSlots;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(int doc_id) {
        this.doc_id = doc_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
}
