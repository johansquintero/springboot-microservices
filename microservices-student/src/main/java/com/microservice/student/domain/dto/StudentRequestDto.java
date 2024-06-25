package com.microservice.student.domain.dto;

public record StudentRequestDto(
        Long id,
        String firstName,
        String lastName,
        int age,
        String email,
        Long courseId)
{}
