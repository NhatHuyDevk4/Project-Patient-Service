package com.pm.patient_service.mapper;


import com.pm.patient_service.dto.PatientResponseDTO;
import com.pm.patient_service.model.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    PatientResponseDTO toPatientResponseDTO(Patient patient);
}
