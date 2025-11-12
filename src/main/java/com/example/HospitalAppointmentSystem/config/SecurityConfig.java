package com.example.HospitalAppointmentSystem.config;


import com.example.HospitalAppointmentSystem.filters.Filter;
import com.example.HospitalAppointmentSystem.model.AppUser;
import com.example.HospitalAppointmentSystem.model.Roles;
import com.example.HospitalAppointmentSystem.repo.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@CrossOrigin
public class SecurityConfig {

    @Autowired
    Filter filter;
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()).
                authorizeHttpRequests((requests) -> requests.requestMatchers("/favicon.ico","/register/patient" , "/login", "/login-register.html", "/dashboard.html", "/css/**", "/js/**").permitAll()
                        .anyRequest().authenticated()
                ).formLogin(form -> form.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

       // http.httpBasic(withDefaults());

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    CommandLineRunner createAdmin(AppUserRepo appUserRepo, PasswordEncoder passwordEncoder) {
        return args -> {
            if (appUserRepo.findByUsername("admin").isEmpty()) {
                AppUser admin = new AppUser();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123")); // your desired password
                admin.setRole(Roles.ROLE_ADMIN);
                appUserRepo.save(admin);
                System.out.println("Admin user created!");
            }
        };
    }



}
