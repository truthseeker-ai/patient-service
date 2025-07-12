package com.hospital.patientservice.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RegistrationRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private LocalDate dateOfBirth;
    private String gender;
    private String address;
    private String medicalHistory;
}
