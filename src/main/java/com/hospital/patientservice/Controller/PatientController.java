package com.hospital.patientservice.Controller;

import com.hospital.patientservice.dto.*;
import com.hospital.patientservice.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService svc;

    @PostMapping("/auth/register")
    public ResponseEntity<PatientProfileDTO> register(@RequestBody RegistrationRequest req) {
        return new ResponseEntity<>(svc.register(req), HttpStatus.CREATED);
    }

    @PostMapping("/auth/login")
    public PatientProfileDTO login(@RequestBody LoginRequest req) {
        return svc.login(req);
    }

    @GetMapping("")
    public List<PatientProfileDTO> listAll() {
        return svc.getAllPatients();
    }

    @GetMapping("/{id}")
    public PatientProfileDTO getOne(@PathVariable Long id) {
        return svc.getPatient(id);
    }

    @PutMapping("/{id}")
    public PatientProfileDTO update(@PathVariable Long id,
                                    @RequestBody RegistrationRequest req) {
        return svc.updatePatient(id, req);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        svc.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}
