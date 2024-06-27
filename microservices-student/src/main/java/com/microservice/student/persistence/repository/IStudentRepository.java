package com.microservice.student.persistence.repository;

import com.microservice.student.domain.dto.StudentRequestDto;
import com.microservice.student.domain.dto.StudentResponseDto;
import com.microservice.student.persistence.entities.Student;

import java.util.List;
import java.util.Optional;


public interface IStudentRepository {
    List<StudentResponseDto> getAll();

    List<StudentResponseDto> getAllByCourseId(Long courseId);
    Optional<StudentResponseDto> getStudentById(Long id);

    Optional<StudentResponseDto> getStudentByEmail(String email);

    Optional<StudentResponseDto> save(StudentRequestDto student);

    void delete(Long id);

}
