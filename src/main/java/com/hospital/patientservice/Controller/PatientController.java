package com.hospital.patientservice.Controller;

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

    // ... existing endpoints ...

    @GetMapping("/search")
    public ResponseEntity<List<DoctorDTO>> searchDoctors(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String specialization
    ) {
        return ResponseEntity.ok(patientService.searchDoctors(name, specialization));
    }
}