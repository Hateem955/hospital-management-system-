package com.example.HospitalAppointmentSystem.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckController {

    @GetMapping("/")
    public String greet(){
        return "Welcome to Hospital Appointment System";
    }
}
