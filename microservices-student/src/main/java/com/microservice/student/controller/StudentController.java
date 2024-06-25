package com.microservice.student.controller;

import com.microservice.student.domain.dto.StudentRequestDto;
import com.microservice.student.domain.dto.StudentResponseDto;
import com.microservice.student.domain.service.IStudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api/students")
@AllArgsConstructor
public class StudentController {
    private final IStudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentResponseDto>> getAll(){
        return ResponseEntity.ok(this.studentService.getAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<StudentResponseDto> getById(@PathVariable(name = "id") Long id){
        return ResponseEntity.of(this.studentService.getStudentById(id));
    }

    @PostMapping
    public ResponseEntity<StudentResponseDto> save(@RequestBody StudentRequestDto student){
        return ResponseEntity.of(this.studentService.save(student));
    }

    @PutMapping
    public ResponseEntity<StudentResponseDto> update(@RequestBody StudentRequestDto student){
        return ResponseEntity.of(this.studentService.update(student));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> update(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(this.studentService.delete(id));
    }

}
