package com.hospital.patientservice.dto;

import lombok.Data;
import java.util.Date;

@Data
public class RegistrationRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private Date dateOfBirth;
    private String gender;
    private String address;
}