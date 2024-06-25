package com.microservice.student.domain.service;

import com.microservice.student.domain.dto.StudentRequestDto;
import com.microservice.student.domain.dto.StudentResponseDto;

import java.util.List;
import java.util.Optional;

public interface IStudentService {
    List<StudentResponseDto> getAll();
    Optional<StudentResponseDto> getStudentById(Long id);

    Optional<StudentResponseDto> save(StudentRequestDto student);

    Optional<StudentResponseDto> update(StudentRequestDto student);

    Boolean delete(Long id);
}
