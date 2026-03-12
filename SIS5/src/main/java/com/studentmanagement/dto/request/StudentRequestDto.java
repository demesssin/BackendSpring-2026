package com.studentmanagement.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(description = "Request body for creating or updating a student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentRequestDto {

    @Schema(description = "First name of the student", example = "Adlet", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 100, message = "First name must be between 2 and 100 characters")
    private String firstName;

    @Schema(description = "Last name of the student", example = "Karimov", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 100, message = "Last name must be between 2 and 100 characters")
    private String lastName;

    @Schema(description = "Email address of the student", example = "adlet.karimov@kbtu.kz", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be a valid email address")
    private String email;

    @Schema(description = "Date of birth", example = "2003-05-15")
    private LocalDate dateOfBirth;

    @Schema(description = "Phone number", example = "+7-707-123-4567")
    @Size(max = 20, message = "Phone number must not exceed 20 characters")
    private String phoneNumber;

    @Schema(description = "Home address", example = "Almaty, Abay ave 52")
    @Size(max = 500, message = "Address must not exceed 500 characters")
    private String address;

    @Schema(description = "Grade Point Average (0.00 - 4.00)", example = "3.75")
    @DecimalMin(value = "0.0", message = "GPA must be at least 0.0")
    @DecimalMax(value = "4.0", message = "GPA must not exceed 4.0")
    private BigDecimal gpa;
}
