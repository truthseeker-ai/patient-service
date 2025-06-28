package com.hospital.patientservice.dto;

import lombok.Builder;
import lombok.Data;
import java.util.Date;

@Data
@Builder
public class PatientProfileDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Date dateOfBirth;
    private String gender;
    private String address;
    private String medicalHistory;
}