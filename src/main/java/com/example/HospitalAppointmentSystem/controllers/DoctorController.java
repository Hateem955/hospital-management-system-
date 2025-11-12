package com.example.HospitalAppointmentSystem.controllers;

import com.example.HospitalAppointmentSystem.model.Doctor;
import com.example.HospitalAppointmentSystem.model.DoctorAppointmentDTO;
import com.example.HospitalAppointmentSystem.model.DoctorResponseDTO;
import com.example.HospitalAppointmentSystem.model.TimeSlotRequestDTO;
import com.example.HospitalAppointmentSystem.repo.DoctorRepo;
import com.example.HospitalAppointmentSystem.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DoctorController {


    @Autowired
    DoctorService doctorService;

    @GetMapping("/doctors")
    @PreAuthorize("hasAnyRole('PATIENT', 'ADMIN')")
    public List<DoctorResponseDTO> getAllDoctors(@RequestParam() int page, @RequestParam() int size){


        return doctorService.viewAllDoctors(page-1, size);

    }

    @GetMapping("/viewMyAppointments")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public List<DoctorAppointmentDTO> viewAllMyAppointment(){
        return doctorService.viewMyAppointments();
    }

    @GetMapping("/viewApprovedAppointments")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public List<DoctorAppointmentDTO> viewAllMyApprovedAppointment(){
        return doctorService.viewMyApprovedAppointments();
    }

    @PutMapping("/approve/{appointment_id}")
    public String approveAppointment(@PathVariable int appointment_id){
        return doctorService.approveAppointment(appointment_id);
    }

    @PostMapping("/create-slot")
    @PreAuthorize("hasRole('ADMIN')")
    public String createTimeSlot(@RequestBody TimeSlotRequestDTO dto) {
        return doctorService.createSlotForDoctor(dto);
    }

}
