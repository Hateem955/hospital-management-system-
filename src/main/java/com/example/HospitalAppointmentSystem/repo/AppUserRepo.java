package com.example.HospitalAppointmentSystem.repo;

import com.example.HospitalAppointmentSystem.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepo extends JpaRepository<AppUser,Integer> {
    Optional<AppUser> findByUsername(String username);
}
