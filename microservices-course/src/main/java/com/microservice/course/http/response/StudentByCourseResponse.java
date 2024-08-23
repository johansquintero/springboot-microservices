package com.microservice.course.http.response;

import com.microservice.course.domain.dto.StudentResponseDto;
import lombok.*;

import java.util.List;

@Getter@Setter@AllArgsConstructor@Builder@NoArgsConstructor
public class StudentByCourseResponse {
    private String name;
    private String teacher;
    private List<StudentResponseDto> students;
}
