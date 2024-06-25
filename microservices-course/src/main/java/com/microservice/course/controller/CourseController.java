package com.microservice.course.controller;

import com.microservice.course.domain.dto.CourseDto;
import com.microservice.course.domain.services.ICourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/courses")
public class CourseController {
    private final ICourseService courseService;

    @GetMapping
    public ResponseEntity<List<CourseDto>> getAll(){
        return ResponseEntity.ok(this.courseService.getAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CourseDto> getById(@PathVariable(name = "id") Long id){
        return ResponseEntity.of(this.courseService.getById(id));
    }

    @GetMapping(path = "/{name}")
    public ResponseEntity<CourseDto> getByName(@PathVariable(name = "name") String name){
        return ResponseEntity.of(this.courseService.getByName(name));
    }

    @PostMapping
    public ResponseEntity<CourseDto> save(@RequestBody CourseDto course){
        return new ResponseEntity<>(this.courseService.save(course).get(), HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<CourseDto> update(@RequestBody CourseDto course){
        return new ResponseEntity<>(this.courseService.update(course).get(), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> update(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(this.courseService.delete(id));
    }
}
