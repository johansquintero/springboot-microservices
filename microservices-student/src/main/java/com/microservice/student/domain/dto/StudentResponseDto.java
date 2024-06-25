package com.microservice.student.domain.dto;

public record StudentResponseDto(
        Long id,
        String firstName,
        String lastName,
        int age,
        String email,
        Long courseId)  {
}
