package com.microservice.course.persistence.repository;

import com.microservice.course.domain.dto.CourseDto;

import java.util.List;
import java.util.Optional;

public interface ICourseRepository {
    List<CourseDto> getAll();
    Optional<CourseDto> getCourseById(Long id);
    Optional<CourseDto> getCourseByName(String name);
    Optional<CourseDto> save(CourseDto course);
    void delete(Long id);
}
