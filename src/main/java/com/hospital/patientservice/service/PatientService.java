package com.hospital.patientservice.service;

import com.hospital.patientservice.dto.*;
import com.hospital.patientservice.entity.Patient;
import com.hospital.patientservice.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository repo;

    public PatientProfileDTO register(RegistrationRequest req) {
        repo.findByEmail(req.getEmail())
                .ifPresent(p -> { throw new RuntimeException("Email already exists"); });

        Patient p = Patient.builder()
                .firstName(req.getFirstName())
                .lastName(req.getLastName())
                .email(req.getEmail())
                .password(req.getPassword())
                .phone(req.getPhone())
                .dateOfBirth(req.getDateOfBirth())
                .gender(req.getGender())
                .address(req.getAddress())
                .medicalHistory(req.getMedicalHistory())
                .build();

        return toDto(repo.save(p));
    }

    public PatientProfileDTO login(LoginRequest req) {
        Patient p = repo.findByEmailAndPassword(req.getEmail(), req.getPassword())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
        return toDto(p);
    }

    public List<PatientProfileDTO> getAllPatients() {
        return repo.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public PatientProfileDTO getPatient(Long id) {
        Patient p = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
        return toDto(p);
    }

    public PatientProfileDTO updatePatient(Long id, RegistrationRequest req) {
        Patient p = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
        p.setFirstName(req.getFirstName());
        p.setLastName(req.getLastName());
        p.setPhone(req.getPhone());
        p.setAddress(req.getAddress());
        p.setMedicalHistory(req.getMedicalHistory());
        return toDto(repo.save(p));
    }

    public void deletePatient(Long id) {
        repo.deleteById(id);
    }

    private PatientProfileDTO toDto(Patient p) {
        return PatientProfileDTO.builder()
                .id(p.getId())
                .firstName(p.getFirstName())
                .lastName(p.getLastName())
                .email(p.getEmail())
                .phone(p.getPhone())
                .dateOfBirth(p.getDateOfBirth())
                .gender(p.getGender())
                .address(p.getAddress())
                .medicalHistory(p.getMedicalHistory())
                .build();
    }
}
