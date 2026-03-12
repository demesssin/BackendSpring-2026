package com.studentmanagement.service;

import com.studentmanagement.dto.request.StudentRequestDto;
import com.studentmanagement.dto.response.StudentResponseDto;
import com.studentmanagement.entity.Student;
import com.studentmanagement.exception.StudentNotFoundException;
import com.studentmanagement.mapper.StudentMapper;
import com.studentmanagement.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Transactional
    public StudentResponseDto createStudent(StudentRequestDto requestDto) {
        log.info("Creating new student: {} {}", requestDto.getFirstName(), requestDto.getLastName());
        log.debug("Student details: email={}", requestDto.getEmail());

        Student student = studentMapper.toEntity(requestDto);
        Student saved = studentRepository.save(student);

        log.info("Student created successfully with id: {}", saved.getId());
        return studentMapper.toResponseDto(saved);
    }

    @Transactional(readOnly = true)
    public Page<StudentResponseDto> getAllStudents(Pageable pageable) {
        log.info("Fetching students page: {}, size: {}", pageable.getPageNumber(), pageable.getPageSize());

        Page<Student> students = studentRepository.findAll(pageable);

        log.debug("Found {} students on page {}", students.getNumberOfElements(), pageable.getPageNumber());
        return students.map(studentMapper::toResponseDto);
    }

    @Transactional(readOnly = true)
    public StudentResponseDto getStudentById(Long id) {
        log.info("Fetching student with id: {}", id);

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

        log.debug("Student found: {} {}", student.getFirstName(), student.getLastName());
        return studentMapper.toResponseDto(student);
    }

    @Transactional
    public StudentResponseDto updateStudent(Long id, StudentRequestDto requestDto) {
        log.info("Updating student with id: {}", id);

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

        studentMapper.updateEntityFromDto(requestDto, student);
        Student updated = studentRepository.save(student);

        log.info("Student updated successfully with id: {}", updated.getId());
        return studentMapper.toResponseDto(updated);
    }

    @Transactional
    public void deleteStudent(Long id) {
        log.info("Deleting student with id: {}", id);

        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException(id);
        }

        studentRepository.deleteById(id);
        log.info("Student deleted successfully with id: {}", id);
    }
}
