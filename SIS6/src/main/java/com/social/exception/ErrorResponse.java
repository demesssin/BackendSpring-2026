package com.social.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Schema(description = "Error response")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {

    @Schema(description = "HTTP status code", example = "400")
    private int status;

    @Schema(description = "Error message", example = "Validation failed")
    private String message;

    @Schema(description = "Field-level errors")
    private Map<String, String> errors;

    @Schema(description = "Timestamp")
    private LocalDateTime timestamp;
}
