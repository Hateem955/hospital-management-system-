package com.example.HospitalAppointmentSystem.service;

import com.example.HospitalAppointmentSystem.model.*;
import com.example.HospitalAppointmentSystem.repo.AppUserRepo;
import com.example.HospitalAppointmentSystem.repo.DoctorRepo;
import com.example.HospitalAppointmentSystem.repo.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    @Autowired
    private AppUserRepo appUserRepo;

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public RegistrationResponseDTO registerPatient(LoginAndRegisterDTO loginAndRegisterDTO){
        AppUser newUser = new AppUser();
        newUser.setUsername(loginAndRegisterDTO.getUsername());
        newUser.setPassword(passwordEncoder.encode(loginAndRegisterDTO.getPassword()));
        newUser.setRole(Roles.ROLE_PATIENT);
        appUserRepo.save(newUser);

        return new RegistrationResponseDTO(newUser.getUser_id());
    }

    public PatientResponseDTO setDetails(PatientsDetailsDTO patientsDetailsDTO){

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        AppUser appUser = appUserRepo.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));


        Patient newPatient = new Patient();
        newPatient.setName(patientsDetailsDTO.getName());
        newPatient.setEmail(patientsDetailsDTO.getEmail());
        newPatient.setAge(patientsDetailsDTO.getAge());
        newPatient.setAppUser(appUser);
        patientRepo.save(newPatient);

        return new PatientResponseDTO(
                newPatient.getPatient_id(),
                newPatient.getName(),
                newPatient.getEmail(),
                newPatient.getAge(),
                newPatient.getAppUser().getUsername()
        );
    }

    public RegistrationResponseDTO registerDoctor(LoginAndRegisterDTO loginAndRegisterDTO) {

        AppUser newUser = new AppUser();
        newUser.setUsername(loginAndRegisterDTO.getUsername());
        newUser.setPassword(passwordEncoder.encode(loginAndRegisterDTO.getPassword()));
        newUser.setRole(Roles.ROLE_DOCTOR);
        appUserRepo.save(newUser);

        return new RegistrationResponseDTO(newUser.getUser_id());
    }

    public DoctorResponseDTO setDoctorDetails(DoctorDetailsDTO doctorDetailsDTO, int appUserId) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        AppUser appUser = appUserRepo.findById(appUserId).orElseThrow(() -> new RuntimeException("User not found"));

        Doctor newDoctor = new Doctor();
        newDoctor.setName(doctorDetailsDTO.getName());
        newDoctor.setSpecialization(doctorDetailsDTO.getSpecialization());
        newDoctor.setYearsOfExperience(doctorDetailsDTO.getYearsOfExperience());
        newDoctor.setAppUser(appUser);
        doctorRepo.save(newDoctor);

        return new DoctorResponseDTO(
                newDoctor.getDoc_id(),
                newDoctor.getName(),
                newDoctor.getSpecialization(),
                newDoctor.getYearsOfExperience(),
                newDoctor.getAppUser().getUsername()
        );
    }
}
