package com.example.HospitalAppointmentSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class HospitalAppointmentSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalAppointmentSystemApplication.class, args);

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		String pass = "bob23";
		// Generate the hash
		String hashedPassword = encoder.encode(pass);

	}
}
