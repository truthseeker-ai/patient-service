package com.hospital.patientservice.dto;

import lombok.Data;

@Data
public class DoctorDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String specialization;
    private String email;
    private String phone;
    private String address;
    private String hospital;
    private Integer yearsOfExperience;
}