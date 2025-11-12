package com.example.HospitalAppointmentSystem.service;

import com.example.HospitalAppointmentSystem.model.*;
import com.example.HospitalAppointmentSystem.repo.AppointmentRepo;
import com.example.HospitalAppointmentSystem.repo.DoctorRepo;
import com.example.HospitalAppointmentSystem.repo.TimeSlotRepo;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class DoctorService {


    @Autowired
    JavaMailSender mailSender;

    @Autowired
    DoctorRepo doctorRepo;

    @Autowired
    AppointmentRepo appointmentRepo;

    @Autowired
    TimeSlotRepo timeSlotRepo;

    @Value("${spring.mail.sender.address}")
    private String mailFromAddress;

    public List<DoctorResponseDTO> viewAllDoctors(int page, int size) {

        Pageable pageable = PageRequest.of(page,size);

        List<Doctor> allDoctors = doctorRepo.findAll(pageable).getContent();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        return allDoctors.stream()
                .map(doctor -> {
                    // Fetch time slots for this doctor
                    List<TimeSlotDTO> slots = timeSlotRepo.findAvailableSlotsByDoctorId(doctor.getDoc_id())
                            .stream()
                            .map(ts -> new TimeSlotDTO(
                                    ts.getId(),
                                    ts.getStartTime().format(formatter),
                                    ts.getEndTime().format(formatter)
                            ))
                            .toList();


                    return new DoctorResponseDTO(
                            doctor.getDoc_id(),
                            doctor.getName(),
                            doctor.getSpecialization(),
                            doctor.getYearsOfExperience(),
                            doctor.getAppUser().getUsername(),
                            slots // <-- pass time slots here
                    );
                })
                .toList();

    }

    public List<DoctorAppointmentDTO> viewMyAppointments() {

       List<Appointments> allAppointments = appointmentRepo.findByStatus("PENDING");

       return allAppointments.stream()
               .map(appointment -> new DoctorAppointmentDTO(
                       appointment.getId(),
                       appointment.getPatient().getName(),
                       appointment.getSymptoms()
               ))
               .toList();

    }

    public String approveAppointment(int appointmentId) {

        Appointments currentAppointment = appointmentRepo.findById(appointmentId).orElseThrow(() -> new RuntimeException("Appointment does not exist"));
        currentAppointment.setStatus("Approved");
        Appointments updatedAppointment = appointmentRepo.save(currentAppointment);

        TimeSlot slot = currentAppointment.getTimeSlot();
        if (slot != null) {
            slot.setBooked(true);
            timeSlotRepo.save(slot);
        }
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true);

            helper.setFrom(this.mailFromAddress);
            helper.setTo(currentAppointment.getPatient().getEmail());
            helper.setSubject("Hospital Appointment Approval");

            String htmlContent = String.format("""
            <html>
            <body style="font-family: Arial, sans-serif; line-height: 1.6; color: #333;">
                <div style="max-width: 600px; margin: 0 auto; padding: 20px; border: 1px solid #ddd; border-radius: 5px;">
                    <h2 style="color: #4CAF50; text-align: center;">Appointment Approved</h2>
                    
                    <p>Dear <strong>%s</strong>,</p>
                    
                    <p>Your appointment has been approved.</p>
                    
                    <div style="background-color: #f9f9f9; padding: 15px; border-radius: 5px; margin: 20px 0;">
                        <h3 style="color: #333; margin-top: 0;">Appointment Details:</h3>
                        <ul style="list-style: none; padding: 0;">
                            <li style="padding: 5px 0;"><strong>Appointment ID:</strong> #%d</li>
                            <li style="padding: 5px 0;"><strong>Doctor:</strong> %s</li>
                            <li style="padding: 5px 0;"><strong>Specialization:</strong> %s</li>
                            <li style="padding: 5px 0;"><strong>Time:</strong> %s</li>
                            <li style="padding: 5px 0;"><strong>Status:</strong> <span style="color: #4CAF50; font-weight: bold;">Approved</span></li>
                        </ul>
                    </div>
                    
                    <p>Please find the attached approval certificate.</p>
                    
                    <p>We look forward to seeing you.</p>
                    
                    <p style="margin-top: 30px;">Best regards,<br><strong>Hospital Management Team</strong></p>
                    
                    <hr style="margin-top: 30px; border: none; border-top: 1px solid #ddd;">
                    
                    <p style="font-size: 12px; color: #777; text-align: center;">
                        For queries, contact: support@hospital.com
                    </p>
                </div>
            </body>
            </html>
            """,
                    currentAppointment.getPatient().getName(),
                    currentAppointment.getId(),
                    currentAppointment.getDoctor().getName(),
                    currentAppointment.getDoctor().getSpecialization(),
                    currentAppointment.getTimeSlot().toString()
            );

            helper.setText(htmlContent, true);

            mailSender.send(message);
            return "Approval Email Sent";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String createSlotForDoctor(TimeSlotRequestDTO dto) {
        Doctor doctor = doctorRepo.findById(dto.getDoctorId()).orElseThrow(() -> new RuntimeException("Doctor not found"));

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalDate today = LocalDate.now();
        LocalTime startTime = LocalTime.parse(dto.getStartTime());
        LocalTime endTime = LocalTime.parse(dto.getEndTime());

        LocalDateTime start = LocalDateTime.of(today, startTime);
        LocalDateTime end = LocalDateTime.of(today, endTime);

        while (start.isBefore(end)){

            TimeSlot slot = new TimeSlot();
            slot.setDoctor(doctor);
            slot.setStartTime(start);
            slot.setEndTime(start.plusHours(1));
            slot.setBooked(false);

            timeSlotRepo.save(slot);

            start = start.plusHours(1);
        }

        return "Slots created";
    }

    public List<DoctorAppointmentDTO> viewMyApprovedAppointments() {
        List<Appointments> allAppointments = appointmentRepo.findByStatus("Approved");

        return allAppointments.stream()
                .map(appointment -> new DoctorAppointmentDTO(
                        appointment.getId(),
                        appointment.getPatient().getName(),
                        appointment.getSymptoms()
                ))
                .toList();
    }
}
