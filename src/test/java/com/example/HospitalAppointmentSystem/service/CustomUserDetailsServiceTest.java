package com.example.HospitalAppointmentSystem.service;


import com.example.HospitalAppointmentSystem.model.AppUser;
import com.example.HospitalAppointmentSystem.repo.AppUserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomUserDetailsServiceTest {

    @Autowired
    private AppUserRepo appUserRepo;

    @Test
    void testLoadByUsername(){
        AppUser appUser = appUserRepo.findByUsername("hateem").orElseThrow(() -> new RuntimeException("User not found"));
        Assertions.assertEquals("hateem", appUser.getUsername());
    }
}
