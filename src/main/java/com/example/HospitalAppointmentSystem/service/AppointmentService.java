package com.example.HospitalAppointmentSystem.service;

import com.example.HospitalAppointmentSystem.controllers.AppointmentController;
import com.example.HospitalAppointmentSystem.model.*;
import com.example.HospitalAppointmentSystem.repo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    DoctorRepo doctorRepo;

    @Autowired
    PatientRepo patientRepo;

    @Autowired
    AppointmentRepo appointmentRepo;

    @Autowired
    AppUserRepo appUserRepo;

    @Autowired
    TimeSlotRepo timeSlotRepo;

    private final static Logger logger = LoggerFactory.getLogger(AppointmentService.class);

    public String bookAppointment(AppointmentRequestDTO appointmentRequestDTO) {

        logger.debug("Starting business logic for appointment booking");

        System.out.println("Looking for doc id: " + appointmentRequestDTO.getDocId());

        Doctor doctor = doctorRepo.findById(appointmentRequestDTO.getDocId()).orElseThrow(() -> new RuntimeException("Doctor not found"));
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Patient patient = patientRepo.findByAppUserUsername(username).orElseThrow(() -> new RuntimeException("Patient not found"));
        TimeSlot slot = timeSlotRepo.findById(appointmentRequestDTO.getSlotId()).orElseThrow(() -> new RuntimeException("Slot does not exist"));

            Appointments appointments = new Appointments();
            appointments.setStatus("PENDING");
            appointments.setDateTime(LocalDateTime.now());
            appointments.setSymptoms(appointmentRequestDTO.getSymptoms());
            appointments.setDoctor(doctor);
            appointments.setPatient(patient);
            appointments.setTimeSlot(slot);

            appointmentRepo.save(appointments);

            return "Appointment Request Sent";


    }

    public List<AppointmentResponseDTO> viewMyAppointment() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Patient patient = patientRepo.findByAppUserUsername(username).orElseThrow(() -> new RuntimeException("Patient not found"));
        List<Appointments> appointments = appointmentRepo.findAllByPatientAppUserUsername(username);

        if (appointments.isEmpty()) {
            throw new RuntimeException("No appointments found");
        }

        return appointments.stream()
                .map(appt -> new AppointmentResponseDTO(
                        appt.getId(),
                        appt.getStatus(),
                        appt.getDateTime(),
                        appt.getDoctor().getName(),
                        appt.getPatient().getName()
                ))
                .toList();

    }

    public String deletePatientById(int appId) {

        AppUser appUser = appUserRepo.findById(appId).orElseThrow(() -> new RuntimeException("User not found"));;
        appUserRepo.delete(appUser);
        return "Patient deleted from Database";
    }
}
