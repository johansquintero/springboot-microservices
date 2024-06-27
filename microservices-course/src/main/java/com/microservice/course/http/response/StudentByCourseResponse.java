package com.microservice.course.http.response;

import com.microservice.course.domain.dto.StudentDto;
import lombok.*;

import java.util.List;

@Getter@Setter@AllArgsConstructor@Builder@NoArgsConstructor
public class StudentByCourseResponse {
    private String courseName;
    private List<StudentDto> studentDtoList;
}
