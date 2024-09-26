package com.microservice.course.persistence.crud;

import com.microservice.course.persistence.entities.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ICourseCrudRepository extends JpaRepository<CourseEntity, Long> {
    Optional<CourseEntity> findCourseById(Long id);

    Optional<CourseEntity> findCourseByName(String name);

    @Query("""
                SELECT c FROM CourseEntity as c
                WHERE c.name LIKE %:value% or c.teacher LIKE %:value%""")
    List<CourseEntity> findCoursesByValues(String value);
}
