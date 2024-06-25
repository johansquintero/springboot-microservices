package com.microservice.course.persistence.repository;

import com.microservice.course.domain.dto.CourseDto;
import com.microservice.course.persistence.crud.ICourseCrudRepository;
import com.microservice.course.persistence.entities.CourseEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class CourseRepositoryImpl implements ICourseRepository{
    private final ICourseCrudRepository courseCrudRepository;

    @Override
    public List<CourseDto> getAll() {
        ModelMapper modelMapper = new ModelMapper();
        return this.courseCrudRepository
                .findAll()
                .stream()
                .map(
                        courseEntity -> modelMapper
                                .map(courseEntity,CourseDto.class))
                .collect(Collectors.toList()
                );
    }

    @Override
    public Optional<CourseDto> getCourseById(Long id) {
        ModelMapper modelMapper = new ModelMapper();
        return this.courseCrudRepository
                .findCourseById(id)
                .map(courseEntity -> modelMapper.map(courseEntity,CourseDto.class));
    }

    @Override
    public Optional<CourseDto> getCourseByName(String name) {
        ModelMapper modelMapper = new ModelMapper();
        return this.courseCrudRepository
                .findCourseByName(name)
                .map(courseEntity -> modelMapper
                        .map(courseEntity,CourseDto.class));
    }

    @Override
    public Optional<CourseDto> save(CourseDto course) {
        ModelMapper modelMapper = new ModelMapper();
        CourseEntity courseEntity = modelMapper.map(course,CourseEntity.class);
        return Optional.of(modelMapper.map(this.courseCrudRepository.save(courseEntity),CourseDto.class));
    }

    @Override
    public void delete(Long id) {
        this.courseCrudRepository.deleteById(id);
    }
}
