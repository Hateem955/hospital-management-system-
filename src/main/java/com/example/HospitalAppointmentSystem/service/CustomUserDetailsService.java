package com.example.HospitalAppointmentSystem.service;

import com.example.HospitalAppointmentSystem.model.AppUser;
import com.example.HospitalAppointmentSystem.repo.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    AppUserRepo appUserRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AppUser appUser = appUserRepo.findByUsername(username)
                .orElseThrow( () -> new UsernameNotFoundException("User Not Found"));

        return new CustomUserDetails(appUser);
    }
}
