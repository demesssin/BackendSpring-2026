package com.studentmanagement.controller;

import com.studentmanagement.dto.request.StudentRequestDto;
import com.studentmanagement.dto.response.ErrorResponseDto;
import com.studentmanagement.dto.response.StudentResponseDto;
import com.studentmanagement.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
@Tag(name = "Student API", description = "CRUD operations for managing students")
public class StudentController {

    private final StudentService studentService;

    @Operation(summary = "Create a new student", description = "Creates a new student record in the system")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Student created successfully",
                    content = @Content(schema = @Schema(implementation = StudentResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Validation error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PostMapping
    public ResponseEntity<StudentResponseDto> createStudent(@Valid @RequestBody StudentRequestDto requestDto) {
        log.info("POST /api/v1/students - creating student");
        StudentResponseDto response = studentService.createStudent(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Get all students (paginated)", description = "Returns a paginated list of all students. Use page, size, and sort query parameters.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Students retrieved successfully")
    })
    @GetMapping
    public ResponseEntity<Page<StudentResponseDto>> getAllStudents(
            @Parameter(description = "Page number (0-based)", example = "0")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Number of items per page", example = "10")
            @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "Sort field and direction (e.g. firstName,asc)", example = "firstName,asc")
            @RequestParam(defaultValue = "id,asc") String sort,
            @PageableDefault(size = 10) Pageable pageable) {
        log.info("GET /api/v1/students - fetching all students");
        return ResponseEntity.ok(studentService.getAllStudents(pageable));
    }

    @Operation(summary = "Get student by ID", description = "Returns a single student by their unique identifier")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Student found",
                    content = @Content(schema = @Schema(implementation = StudentResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Student not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDto> getStudentById(
            @Parameter(description = "Student ID", example = "1")
            @PathVariable Long id) {
        log.info("GET /api/v1/students/{} - fetching student", id);
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @Operation(summary = "Update student", description = "Updates an existing student record by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Student updated successfully",
                    content = @Content(schema = @Schema(implementation = StudentResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Validation error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Student not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDto> updateStudent(
            @Parameter(description = "Student ID", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody StudentRequestDto requestDto) {
        log.info("PUT /api/v1/students/{} - updating student", id);
        return ResponseEntity.ok(studentService.updateStudent(id, requestDto));
    }

    @Operation(summary = "Delete student", description = "Deletes a student record by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Student deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Student not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(
            @Parameter(description = "Student ID", example = "1")
            @PathVariable Long id) {
        log.info("DELETE /api/v1/students/{} - deleting student", id);
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
