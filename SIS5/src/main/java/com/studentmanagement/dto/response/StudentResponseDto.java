package com.studentmanagement.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Schema(description = "Response body containing student information")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentResponseDto {

    @Schema(description = "Unique identifier of the student", example = "1")
    private Long id;

    @Schema(description = "First name", example = "Adlet")
    private String firstName;

    @Schema(description = "Last name", example = "Karimov")
    private String lastName;

    @Schema(description = "Email address", example = "adlet.karimov@kbtu.kz")
    private String email;

    @Schema(description = "Date of birth", example = "2003-05-15")
    private LocalDate dateOfBirth;

    @Schema(description = "Phone number", example = "+7-707-123-4567")
    private String phoneNumber;

    @Schema(description = "Home address", example = "Almaty, Abay ave 52")
    private String address;

    @Schema(description = "Grade Point Average", example = "3.75")
    private BigDecimal gpa;

    @Schema(description = "Record creation timestamp", example = "2026-03-12T10:00:00")
    private LocalDateTime createdAt;

    @Schema(description = "Record last update timestamp", example = "2026-03-12T10:00:00")
    private LocalDateTime updatedAt;
}
