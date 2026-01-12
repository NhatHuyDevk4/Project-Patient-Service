package com.pm.patient_service.dto;

import com.pm.patient_service.dto.validators.CreatePatientValidataionGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PatientRequestDTO {
    @NotBlank(message = "Name is mandatory")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    String email;

    @NotBlank(message = "Address is required")
    String address;

    @NotBlank(message = "Date of Birth is required")
    String dateOfBirth;

    @NotBlank(message = "Registered date is required")
    String registeredDate;
}

// Khi nào dùng @NotBlank
//==> @NotBlank được sử dụng cho các trường kiểu String để đảm bảo rằng chuỗi không rỗng và không chỉ chứa khoảng trắng. Nó kiểm tra cả null, chuỗi rỗng ("") và chuỗi chỉ có khoảng trắng ("   ").
// Khi nào dùng @NotNull
//==> @NotNull được sử dụng cho các trường kiểu bất kỳ (không chỉ String) để đảm bảo rằng giá trị không phải là null. Tuy nhiên, nó không kiểm tra