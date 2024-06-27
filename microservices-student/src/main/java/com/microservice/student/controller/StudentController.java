package com.microservice.student.controller;

import com.microservice.student.domain.dto.StudentRequestDto;
import com.microservice.student.domain.dto.StudentResponseDto;
import com.microservice.student.domain.service.IStudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api/student")
@AllArgsConstructor
public class StudentController {
    private final IStudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentResponseDto>> getAll(){
        return ResponseEntity.ok(this.studentService.getAll());
    }

    @GetMapping(path = "/search-by-course/{courseId}")
    public ResponseEntity<List<StudentResponseDto>> getAllByCourse(@PathVariable(name = "courseId") Long courseId){
        return ResponseEntity.ok(this.studentService.getAllByCourseId(courseId));
    }

    @GetMapping(path = "/search-by-id/{id}")
    public ResponseEntity<StudentResponseDto> getById(@PathVariable(name = "id") Long id){
        return ResponseEntity.of(this.studentService.getStudentById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<StudentResponseDto> save(@RequestBody StudentRequestDto student){
        return ResponseEntity.of(this.studentService.save(student));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<StudentResponseDto> update(@RequestBody StudentRequestDto student){
        return ResponseEntity.of(this.studentService.update(student));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(this.studentService.delete(id));
    }

}
