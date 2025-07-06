package com.hospital.patientservice.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class AppointmentDTO {
    private Long id;
    private Long doctorId;
    private LocalDate appointmentDate;
    private String appointmentSlot;
    private String status;
}
