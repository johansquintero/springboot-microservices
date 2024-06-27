package com.microservice.course.domain.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor@NoArgsConstructor
@Builder
public class StudentDto{
        Long id;
        String firstName;
        String lastName;
        int age;
        String email;
        Long courseId;
}
