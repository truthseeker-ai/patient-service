package com.hospital.patientservice.service;

import com.hospital.patientservice.dto.*;
import com.hospital.patientservice.entity.Patient;
import com.hospital.patientservice.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    private final RestTemplate restTemplate;

    @Value("${doctor.service.url}")
    private String doctorServiceUrl;

    // ... existing methods ...

    public List<DoctorDTO> searchDoctors(String name, String specialization) {
        String url = doctorServiceUrl + "/api/doctors/search?";

        if (name != null && !name.isEmpty()) {
            url += "name=" + name + "&";
        }

        if (specialization != null && !specialization.isEmpty()) {
            url += "specialization=" + specialization;
        }

        // Remove trailing & if exists
        if (url.endsWith("&")) {
            url = url.substring(0, url.length() - 1);
        }

        DoctorDTO[] doctors = restTemplate.getForObject(url, DoctorDTO[].class);
        return Arrays.asList(doctors);
    }
}