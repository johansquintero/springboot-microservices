package com.microservice.student.persistence.crud;

import com.microservice.student.persistence.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IStudentCrudRepository extends JpaRepository<Student,Long> {
    Optional<Student> findStudentById(Long id);
    List<Student> findAllByCourseId(Long courseId);
    Optional<Student> findStudentByEmail(String email);
    Optional<Student> findStudentByFirstName(String name);

    Optional<Student> findStudentByLastName(String lastName);

    @Query("""
            SELECT s FROM Student s
            WHERE s.firstName LIKE %:value% or s.lastName LIKE %:value% or s.email LIKE %:value%""")
    List<Student> getAllByAttributes(String value);
}
