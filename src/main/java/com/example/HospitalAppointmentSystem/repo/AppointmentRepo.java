package com.example.HospitalAppointmentSystem.repo;

import com.example.HospitalAppointmentSystem.model.Appointments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointments,Integer> {
    List<Appointments> findAllByPatientAppUserUsername(String Username);
    List<Appointments> findByStatus(String status);

}
