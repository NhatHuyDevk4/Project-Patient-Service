package com.pm.patient_service.mapper;


import com.pm.patient_service.dto.PatientRequestDTO;
import com.pm.patient_service.dto.PatientResponseDTO;
import com.pm.patient_service.model.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    Patient toPatient(PatientRequestDTO patientRequestDTO);
    PatientResponseDTO toPatientResponseDTO(Patient patient);
}
