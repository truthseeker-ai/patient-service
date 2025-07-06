package com.hospital.patientservice.controller;

import com.hospital.patientservice.dto.*;
import com.hospital.patientservice.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PostMapping("/auth/register")
    public ResponseEntity<PatientProfileDTO> register(@RequestBody RegistrationRequest request) {
        return ResponseEntity.ok(patientService.register(request));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<PatientProfileDTO> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(patientService.login(request));
    }

    @GetMapping("/search")
    public ResponseEntity<List<DoctorDTO>> searchDoctors(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String specialization) {
        return ResponseEntity.ok(patientService.searchDoctors(name, specialization));
    }

    //Get all patients
    @GetMapping("")
    public ResponseEntity<List<PatientProfileDTO>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }
}
