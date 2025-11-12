package com.example.HospitalAppointmentSystem.controllers;

import com.example.HospitalAppointmentSystem.model.LoginAndRegisterDTO;
import com.example.HospitalAppointmentSystem.model.LoginResponseDTO;
import com.example.HospitalAppointmentSystem.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Login {

    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginAndRegisterDTO loginDTO) {
        String token = loginService.checkUserDetails(loginDTO); // existing token generation
        if (token != null) {
            String role = loginService.getRoleForUser(loginDTO.getUsername());
            return ResponseEntity.ok(new LoginResponseDTO(token, role));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

}
