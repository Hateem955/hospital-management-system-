package com.example.HospitalAppointmentSystem.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AppointmentRequestDTO {

    @JsonProperty("doc_id")  // maps "doc_id" from JSON to this field
    private Integer docId;

    private String symptoms;

    @JsonProperty("slot_id")  // maps "slot_id" from JSON to this field
    private Integer slotId;

    public AppointmentRequestDTO() {
    }




    public Integer getSlotId() {
        return slotId;
    }

    public void setSlotId(Integer slotId) {
        this.slotId = slotId;
    }

    public Integer getDocId() {
        return docId;
    }

    public void setDocId(Integer docId) {
        this.docId = docId;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }
}
