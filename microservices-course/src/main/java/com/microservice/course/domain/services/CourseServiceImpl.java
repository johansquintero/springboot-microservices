package com.microservice.course.domain.services;

import com.microservice.course.client.IStudentClient;
import com.microservice.course.domain.dto.CourseDto;
import com.microservice.course.domain.dto.StudentDto;
import com.microservice.course.exception.ErrorValidationExceptions;
import com.microservice.course.http.response.StudentByCourseResponse;
import com.microservice.course.persistence.repository.ICourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements ICourseService{
    private final ICourseRepository courseRepository;
    private final IStudentClient studentClient;

    @Override
    public List<CourseDto> getAll() {
        return this.courseRepository.getAll();
    }

    @Override
    public Optional<CourseDto> getById(Long id) {
        return this.courseRepository.getCourseById(id);
    }

    @Override
    public Optional<CourseDto> getByName(String name) {
        return this.courseRepository.getCourseByName(name);
    }

    @Override
    public Optional<CourseDto> save(CourseDto course) {
        Optional<CourseDto> courseOpt = this.courseRepository.getCourseByName(course.getName());
        if (courseOpt.isPresent()){
            throw  new ErrorValidationExceptions("El curso ya se encuetra registrado");
        }
        return this.courseRepository.save(course);
    }

    @Override
    public Optional<CourseDto> update(CourseDto course) {
        Optional<CourseDto> courseOpt = this.courseRepository.getCourseByName(course.getName());
        if (courseOpt.isEmpty()){
            throw  new ErrorValidationExceptions("El curso no se encuetra registrado");
        }
        return this.courseRepository.save(course);
    }

    @Override
    public boolean delete(Long id) {
        Optional<CourseDto> courseOpt = this.courseRepository.getCourseById(id);
        if (courseOpt.isEmpty()){
            return false;
        }
        this.courseRepository.delete(id);
        return true;
    }

    @Override
    public StudentByCourseResponse findStudenByCourseId(Long courseId) {
        Optional<CourseDto> courseOpt = this.courseRepository.getCourseById(courseId);
        if (courseOpt.isEmpty()){
            throw  new ErrorValidationExceptions("El curso no se encuetra registrado");
        }
        List<StudentDto> studentDtoList = studentClient.findAllStudentsByCourseId(courseOpt.get().getId());
        return StudentByCourseResponse.builder()
                .courseName(courseOpt.get().getName())
                .studentDtoList(studentDtoList)
                .build();
    }

}
