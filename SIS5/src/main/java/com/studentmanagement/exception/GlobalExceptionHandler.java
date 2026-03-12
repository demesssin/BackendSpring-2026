package com.studentmanagement.exception;

import com.studentmanagement.dto.response.ErrorResponseDto;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    @ApiResponse(responseCode = "404", description = "Student not found")
    public ResponseEntity<ErrorResponseDto> handleStudentNotFound(StudentNotFoundException ex) {
        log.error("Resource not found: {}", ex.getMessage());

        ErrorResponseDto error = ErrorResponseDto.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ApiResponse(responseCode = "400", description = "Validation error")
    public ResponseEntity<ErrorResponseDto> handleValidationErrors(MethodArgumentNotValidException ex) {
        log.error("Validation failed: {}", ex.getMessage());

        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(err -> fieldErrors.put(err.getField(), err.getDefaultMessage()));

        ErrorResponseDto error = ErrorResponseDto.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Validation failed")
                .errors(fieldErrors)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Exception.class)
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<ErrorResponseDto> handleGenericException(Exception ex) {
        log.error("Unexpected error occurred: ", ex);

        ErrorResponseDto error = ErrorResponseDto.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("An unexpected error occurred")
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
