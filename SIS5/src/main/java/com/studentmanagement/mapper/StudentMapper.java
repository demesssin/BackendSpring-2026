package com.studentmanagement.mapper;

import com.studentmanagement.dto.request.StudentRequestDto;
import com.studentmanagement.dto.response.StudentResponseDto;
import com.studentmanagement.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.BeanMapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    Student toEntity(StudentRequestDto dto);

    StudentResponseDto toResponseDto(Student entity);

    List<StudentResponseDto> toResponseDtoList(List<Student> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(StudentRequestDto dto, @MappingTarget Student entity);
}
