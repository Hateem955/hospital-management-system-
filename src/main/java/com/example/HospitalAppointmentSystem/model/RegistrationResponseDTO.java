package com.example.HospitalAppointmentSystem.model;

public class RegistrationResponseDTO {
    private int userId;

    public RegistrationResponseDTO() {
    }

    public RegistrationResponseDTO(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
