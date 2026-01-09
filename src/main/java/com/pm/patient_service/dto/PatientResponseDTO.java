package com.pm.patient_service.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PatientResponseDTO {
    String id;
    String name;
    String email;
    String address;
    String dateOfBirth;
}
