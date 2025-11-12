package com.example.HospitalAppointmentSystem.repo;

import com.example.HospitalAppointmentSystem.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepo extends JpaRepository<Patient,Integer> {

    Optional<Patient> findByAppUserUsername(String username);
}
