package com.pm.patient_service.controller;

import com.pm.patient_service.dto.PatientResponseDTO;
import com.pm.patient_service.service.PatientService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    @GetMapping("/list")
    ResponseEntity<List<PatientResponseDTO>> getPatients() {
        List<PatientResponseDTO> patients = patientService.getPatients();
        return ResponseEntity.ok().body(patients);
    }


}
