package com.pm.patient_service.service;

import com.pm.patient_service.dto.PatientRequestDTO;
import com.pm.patient_service.dto.PatientResponseDTO;
import com.pm.patient_service.exception.EmailAlreadyException;
import com.pm.patient_service.exception.PatientNotFoundException;
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
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PatientService {
    PatientRepository patientRepository;
    PatientMapper patientMapper;

    // Get list of patients
    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();

        patients.forEach(patient ->
                log.info("Patient ID: {}, Name: {}, Email: {}",
                        patient.getId(), patient.getName() , patient.getEmail())
        );

        List<PatientResponseDTO> patientResponseDTOS = patients.stream()
                .map(patientMapper::toPatientResponseDTO)
                .collect(Collectors.toList());

        patientResponseDTOS.forEach(patientResponseDTO ->
                log.info("PatientResponseDTO ID: {}, Name: {}, Email: {}",
                        patientResponseDTO.getId(), patientResponseDTO.getName() , patientResponseDTO.getEmail())
        );

        log.info("Fetched {} patients from the database.", patientResponseDTOS.size());
        return patientResponseDTOS;
    }

    // Create a new patient
    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        if(patientRepository.existsByEmail(patientRequestDTO.getEmail())){
            throw new EmailAlreadyException("Email "+ patientRequestDTO.getEmail() +" already exists.");
        }
        //1. Convert PatientRequestDTO to Patient entity
       Patient patient = patientMapper.toPatient(patientRequestDTO);

       //2. Save Patient entity to database
       patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
       patient.setRegisteredDate(LocalDate.parse(patientRequestDTO.getRegisteredDate()));

       //3 save to db
        Patient savedPatient = patientRepository.save(patient);

       return patientMapper.toPatientResponseDTO(savedPatient);
    }

    // Update patient details
    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with id: " + id));


        if(patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(), id)){
            throw new EmailAlreadyException("Email "+ patientRequestDTO.getEmail() +" already exists.");
        }

        // Update patient details
        patient.setName(patientRequestDTO.getName());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
        patient.setRegisteredDate(LocalDate.parse(patientRequestDTO.getRegisteredDate()));

        Patient updatedPatient = patientRepository.save(patient);

        return patientMapper.toPatientResponseDTO(updatedPatient);
    }


   public void deletePatient(UUID id) {
        patientRepository.deleteById(id);
   }
}
