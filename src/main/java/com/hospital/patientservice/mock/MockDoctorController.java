package com.hospital.patientservice.mock;

import com.hospital.patientservice.dto.DoctorDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/doctors")
public class MockDoctorController {

    @GetMapping("/search")
    public List<DoctorDTO> searchDoctors(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String specialization) {

        // Mock data
        DoctorDTO doctor1 = new DoctorDTO();
        doctor1.setId(1L);
        doctor1.setFirstName("John");
        doctor1.setLastName("Doe");
        doctor1.setSpecialization("Cardiologist");
        doctor1.setEmail("john.doe@hospital.com");
        doctor1.setPhone("123-456-7890");
        doctor1.setAddress("123 Medical Center");
        doctor1.setHospital("City General Hospital");
        doctor1.setYearsOfExperience(10);

        DoctorDTO doctor2 = new DoctorDTO();
        doctor2.setId(2L);
        doctor2.setFirstName("Sarah");
        doctor2.setLastName("Smith");
        doctor2.setSpecialization("Pediatrician");
        doctor2.setEmail("sarah.smith@hospital.com");
        doctor2.setPhone("987-654-3210");
        doctor2.setAddress("456 Children's Hospital");
        doctor2.setHospital("Children's Medical Center");
        doctor2.setYearsOfExperience(8);

        DoctorDTO doctor3 = new DoctorDTO();
        doctor3.setId(3L);
        doctor3.setFirstName("Michael");
        doctor3.setLastName("Johnson");
        doctor3.setSpecialization("Ophthalmologist");
        doctor3.setEmail("michael.johnson@hospital.com");
        doctor3.setPhone("555-123-4567");
        doctor3.setAddress("789 Eye Care Center");
        doctor3.setHospital("Vision Hospital");
        doctor3.setYearsOfExperience(12);

        // Filter based on parameters
        List<DoctorDTO> allDoctors = Arrays.asList(doctor1, doctor2, doctor3);

        if (name != null && !name.isEmpty()) {
            String searchName = name.toLowerCase();
            allDoctors = allDoctors.stream()
                    .filter(d -> d.getFirstName().toLowerCase().contains(searchName) ||
                            d.getLastName().toLowerCase().contains(searchName))
                    .collect(Collectors.toList());
        }

        if (specialization != null && !specialization.isEmpty()) {
            String searchSpec = specialization.toLowerCase();
            allDoctors = allDoctors.stream()
                    .filter(d -> d.getSpecialization().toLowerCase().contains(searchSpec))
                    .collect(Collectors.toList());
        }

        return allDoctors;
    }
}