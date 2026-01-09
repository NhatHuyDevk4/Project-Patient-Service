package com.pm.patient_service.service;

import com.pm.patient_service.dto.PatientRequestDTO;
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

import java.time.LocalDate;
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

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        //1. Convert PatientRequestDTO to Patient entity
       Patient patient = patientMapper.toPatient(patientRequestDTO);

       //2. Save Patient entity to database
       patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
       patient.setRegisteredDate(LocalDate.parse(patientRequestDTO.getRegisteredDate()));

       //3 save to db
        Patient savedPatient = patientRepository.save(patient);

       return patientMapper.toPatientResponseDTO(savedPatient);
    }
}
