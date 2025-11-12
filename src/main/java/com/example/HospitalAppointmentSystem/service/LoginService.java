package com.example.HospitalAppointmentSystem.service;

import com.example.HospitalAppointmentSystem.model.LoginAndRegisterDTO;
import com.example.HospitalAppointmentSystem.repo.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    AppUserRepo appUserRepo;

    @Autowired
    JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;


    public String checkUserDetails(LoginAndRegisterDTO loginAndRegisterDTO) {

        try {
            // Try to authenticate user with username and raw password
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginAndRegisterDTO.getUsername(), loginAndRegisterDTO.getPassword())
            );


            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return jwtService.generateToken(userDetails.getUsername());


        } catch (BadCredentialsException ex) {
            // Invalid username or password
            return null;
        }
    }

    public String getRoleForUser(String username) {
        return appUserRepo.findByUsername(username)
                .map(user -> user.getRole().name()) // assuming enum Roles
                .orElse(null);
    }
}
