package com.example.HospitalAppointmentSystem.repo;

import com.example.HospitalAppointmentSystem.model.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeSlotRepo extends JpaRepository<TimeSlot,Integer> {
    @Query("SELECT t FROM TimeSlot t WHERE t.doctor.doc_id = :docId AND t.isBooked = false")
    List<TimeSlot> findAvailableSlotsByDoctorId(@Param("docId") int docId);
}

