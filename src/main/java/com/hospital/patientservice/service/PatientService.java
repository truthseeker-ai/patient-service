package com.hospital.patientservice.service;

import com.hospital.patientservice.dto.*;
import com.hospital.patientservice.entity.Patient;
import com.hospital.patientservice.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final RestTemplate restTemplate;

    private final String doctorServiceUrl = "http://localhost:8082";

    public PatientProfileDTO register(RegistrationRequest req) {
        Patient p = new Patient();
        p.setFirstName(req.getFirstName());
        p.setLastName(req.getLastName());
        p.setEmail(req.getEmail());
        p.setPassword(req.getPassword());
        p.setPhone(req.getPhone());
        p.setDateOfBirth(req.getDateOfBirth());
        p.setGender(req.getGender());
        p.setAddress(req.getAddress());
        p.setMedicalHistory(req.getMedicalHistory());
        p = patientRepository.save(p);
        return toDto(p);
    }

    public PatientProfileDTO login(LoginRequest req) {
        Patient p = patientRepository.findByEmailAndPassword(req.getEmail(), req.getPassword())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
        return toDto(p);
    }

    public List<PatientProfileDTO> getAllPatients() {
        return patientRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<DoctorDTO> searchDoctors(String name, String specialization) {
        DoctorDTO[] arr = restTemplate.getForObject(
                doctorServiceUrl + "/api/doctors", DoctorDTO[].class
        );
        return List.of(arr).stream()
                .filter(d -> (name == null || d.getFirstName().toLowerCase().contains(name.toLowerCase()))
                        && (specialization == null || d.getSpecialization().toLowerCase().contains(specialization.toLowerCase())))
                .collect(Collectors.toList());
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
