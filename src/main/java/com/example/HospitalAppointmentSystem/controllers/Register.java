package com.example.HospitalAppointmentSystem.controllers;

import com.example.HospitalAppointmentSystem.model.*;
import com.example.HospitalAppointmentSystem.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/register")
public class Register {

    @Autowired
    RegisterService registerService;

    @PostMapping("/patient")
    public RegistrationResponseDTO registerPatient(@RequestBody LoginAndRegisterDTO loginAndRegisterDTO){
        return registerService.registerPatient(loginAndRegisterDTO);
    }

    // to-do: only patient and admin can have access to this api ////DONE/////
    @PostMapping("/setPatient")
    @PreAuthorize("hasAnyRole('ADMIN', 'PATIENT')")
    public PatientResponseDTO setDetails(@RequestBody PatientsDetailsDTO patientsDetailsDTO){

        return registerService.setDetails(patientsDetailsDTO);
    }

    //to-do : only admin can have access to this api ////DONE/////
    @PostMapping("/doctor")
    @PreAuthorize("hasRole('ADMIN')")
    public RegistrationResponseDTO registerDoctor(@RequestBody LoginAndRegisterDTO loginAndRegisterDTO){
        return registerService.registerDoctor(loginAndRegisterDTO);
    }

    //to-do : only admin can have access to this api ////DONE/////
    @PostMapping("/setDoctor/{appUserId}")
    @PreAuthorize("hasRole('ADMIN')")
    public DoctorResponseDTO setDetails(@RequestBody DoctorDetailsDTO doctorDetailsDTO, @PathVariable int appUserId){

        return registerService.setDoctorDetails(doctorDetailsDTO,appUserId);
    }
}
