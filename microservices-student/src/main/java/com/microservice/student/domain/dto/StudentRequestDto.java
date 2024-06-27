package com.microservice.student.domain.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentRequestDto{
        Long id;
        String firstName;
        String lastName;
        int age;
        String email;
        Long courseId;
}
