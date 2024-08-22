package com.microservice.student.persistence.repository;

import com.microservice.student.domain.dto.StudentRequestDto;
import com.microservice.student.domain.dto.StudentResponseDto;
import com.microservice.student.persistence.crud.IStudentCrudRepository;
import com.microservice.student.persistence.entities.Student;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Repository
public class StudentRepositoryImpl implements IStudentRepository{
    private final IStudentCrudRepository repository;

    @Override
    public List<StudentResponseDto> getAll() {
        ModelMapper modelMapper = new ModelMapper();
        return repository.findAll().stream().map(student->
            modelMapper.map(student,StudentResponseDto.class)
        ).collect(Collectors.toList());
    }

    @Override
    public List<StudentResponseDto> getAllByCourseId(Long courseId) {
        ModelMapper modelMapper = new ModelMapper();
        return this.repository
                .findAllByCourseId(courseId)
                .stream()
                .map(
                        student -> modelMapper.map(student,StudentResponseDto.class)
                )
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentResponseDto> getAllByAttributes(String value) {
        ModelMapper modelMapper = new ModelMapper();
        return this.repository
                .getAllByAttributes(value)
                .stream()
                .map(
                        student -> modelMapper.map(student,StudentResponseDto.class)
                )
                .collect(Collectors.toList());
    }

    @Override
    public Optional<StudentResponseDto> getStudentById(Long id) {
        Optional<Student> studentOpt = this.repository.findStudentById(id);
        if (studentOpt.isEmpty()){
            return Optional.empty();
        }
        ModelMapper modelMapper= new ModelMapper();
        return studentOpt.map(student -> modelMapper.map(student, StudentResponseDto.class));
    }

    @Override
    public Optional<StudentResponseDto> getStudentByFirstName(String name) {
        Optional<Student> studentOpt = this.repository.findStudentByFirstName(name);
        if (studentOpt.isEmpty()){
            return Optional.empty();
        }
        ModelMapper modelMapper= new ModelMapper();
        return studentOpt.map(student -> modelMapper.map(student, StudentResponseDto.class));
    }

    @Override
    public Optional<StudentResponseDto> getStudentByLastName(String lastName) {
        Optional<Student> studentOpt = this.repository.findStudentByLastName(lastName);
        if (studentOpt.isEmpty()){
            return Optional.empty();
        }
        ModelMapper modelMapper= new ModelMapper();
        return studentOpt.map(student -> modelMapper.map(student, StudentResponseDto.class));
    }

    @Override
    public Optional<StudentResponseDto> getStudentByEmail(String email) {
        Optional<Student> studentOpt = this.repository.findStudentByEmail(email);
        if (studentOpt.isEmpty()){
            return Optional.empty();
        }
        ModelMapper modelMapper= new ModelMapper();
        return studentOpt.map(student -> modelMapper.map(student, StudentResponseDto.class));
    }

    @Override
    public Optional<StudentResponseDto> save(StudentRequestDto student) {
        ModelMapper modelMapper = new ModelMapper();
        Student studentEntity = modelMapper.map(student,Student.class);
        Optional<Student> saved = Optional.of( this.repository.save(studentEntity));
        return saved.map(entity->modelMapper.map(entity, StudentResponseDto.class));
    }

    @Override
    public void delete(Long id) {
        this.repository.deleteById(id);
    }
}
