package com.microservice.course.domain.services;

import com.microservice.course.domain.dto.CourseDto;

import java.util.List;
import java.util.Optional;

public interface ICourseService {
    List<CourseDto> getAll();
    Optional<CourseDto> getById(Long id);
    Optional<CourseDto> getByName(String name);
    Optional<CourseDto> save(CourseDto course);
    Optional<CourseDto> update(CourseDto courseDto);
    boolean delete (Long id);
}
