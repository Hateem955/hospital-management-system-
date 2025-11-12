package com.example.HospitalAppointmentSystem.model;

public class TimeSlotDTO {

    private int slot_id;
    private String startTime;
    private String endTime;


    public TimeSlotDTO(int slot_id, String startTime, String endTime) {
        this.slot_id = slot_id;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getSlot_id() {
        return slot_id;
    }

    public void setSlot_id(int slot_id) {
        this.slot_id = slot_id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
