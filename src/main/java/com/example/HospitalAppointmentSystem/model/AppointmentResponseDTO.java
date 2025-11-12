package com.example.HospitalAppointmentSystem.model;

import java.time.LocalDateTime;

public class AppointmentResponseDTO {

    private int id;
    private String status;
    private LocalDateTime dateTime;
    private String docName;
    private String patient_name;

    public AppointmentResponseDTO(int id, String status, LocalDateTime dateTime, String docName, String patient_name) {
        this.id = id;
        this.status = status;
        this.dateTime = dateTime;
        this.docName = docName;
        this.patient_name = patient_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }
}
