package com.microservice.student.persistence.crud;

import com.microservice.student.persistence.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IStudentCrudRepository extends JpaRepository<Student,Long> {
    Optional<Student> findStudentById(Long id);

    Optional<Student> findStudentByEmail(String email);
}
