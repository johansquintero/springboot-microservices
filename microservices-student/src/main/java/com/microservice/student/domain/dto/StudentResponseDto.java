package com.microservice.student.domain.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentResponseDto{
        Long id;
        String firstName;
        String lastName;
        int age;
        String email;
        Long courseId;
}
