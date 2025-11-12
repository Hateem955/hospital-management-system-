package com.example.HospitalAppointmentSystem.controllers;

import com.example.HospitalAppointmentSystem.model.AppointmentRequestDTO;
import com.example.HospitalAppointmentSystem.model.AppointmentResponseDTO;
import com.example.HospitalAppointmentSystem.model.Appointments;
import com.example.HospitalAppointmentSystem.service.AppointmentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    private final static Logger logger = LoggerFactory.getLogger(AppointmentController.class);

    @PostMapping("/bookAppointment")
    @PreAuthorize("hasAnyRole('ADMIN', 'PATIENT')")
    public String book(@RequestBody AppointmentRequestDTO appointmentRequestDTO){

        logger.info("Booking appointment");

        return appointmentService.bookAppointment(appointmentRequestDTO);
    }

    @GetMapping("/viewMyAppointment")
    @PreAuthorize("hasAnyRole('ADMIN', 'PATIENT')")
    public List<AppointmentResponseDTO> viewAppointment(){
        return appointmentService.viewMyAppointment();
    }

    @DeleteMapping("/deletePatient/{app_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deletePatient(@PathVariable int app_id){
        return appointmentService.deletePatientById(app_id);
    }



}
