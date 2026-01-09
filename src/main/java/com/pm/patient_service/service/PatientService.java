package com.pm.patient_service.service;

import com.pm.patient_service.dto.PatientResponseDTO;
import com.pm.patient_service.mapper.PatientMapper;
import com.pm.patient_service.model.Patient;
import com.pm.patient_service.repository.PatientRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PatientService {
    PatientRepository patientRepository;
    PatientMapper patientMapper;

    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();

        List<PatientResponseDTO> patientResponseDTOS = patients.stream()
                .map(patientMapper::toPatientResponseDTO)
                .collect(Collectors.toList());

        log.info("Fetched {} patients from the database.", patientResponseDTOS.size());
        return patientResponseDTOS;
    }
}
