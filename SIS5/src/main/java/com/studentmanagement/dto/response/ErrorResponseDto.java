package com.studentmanagement.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Schema(description = "Error response returned when a request fails")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponseDto {

    @Schema(description = "HTTP status code", example = "400")
    private int status;

    @Schema(description = "Error message", example = "Validation failed")
    private String message;

    @Schema(description = "Field-level validation errors", example = "{\"email\": \"Email must be a valid email address\"}")
    private Map<String, String> errors;

    @Schema(description = "Timestamp of the error", example = "2026-03-12T10:00:00")
    private LocalDateTime timestamp;
}
