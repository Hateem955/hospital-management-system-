package com.example.HospitalAppointmentSystem.model;

public class DoctorAppointmentDTO {

    private int appointment_id;
    private String patient_name;
    private String symptoms;

    public DoctorAppointmentDTO(int appointment_id, String patient_name, String symptoms) {
        this.appointment_id = appointment_id;
        this.patient_name = patient_name;
        this.symptoms = symptoms;
    }

    public int getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(int appointment_id) {
        this.appointment_id = appointment_id;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }
}
