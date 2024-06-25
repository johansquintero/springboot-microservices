package com.microservice.course.persistence.crud;

import com.microservice.course.persistence.entities.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICourseCrudRepository extends JpaRepository<CourseEntity,Long> {
    Optional<CourseEntity> findCourseById(Long id);
    Optional<CourseEntity> findCourseByName(String name);
}
