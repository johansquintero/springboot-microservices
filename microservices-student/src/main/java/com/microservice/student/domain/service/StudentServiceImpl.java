package com.microservice.student.domain.service;

import com.microservice.student.domain.dto.StudentRequestDto;
import com.microservice.student.domain.dto.StudentResponseDto;
import com.microservice.student.exception.ErrorValidationExceptions;
import com.microservice.student.persistence.repository.IStudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements IStudentService{
    private final IStudentRepository studentRepository;

    @Override
    public List<StudentResponseDto> getAll() {
        return this.studentRepository.getAll();
    }

    @Override
    public Optional<StudentResponseDto> getStudentById(Long id) {
        return this.studentRepository.getStudentById(id);
    }

    @Override
    public Optional<StudentResponseDto> save(StudentRequestDto student) {
        var studentOpt = this.studentRepository.getStudentByEmail(student.email());
        if (studentOpt.isPresent()){
            throw new ErrorValidationExceptions("El estudiante ya se encuetra registrado");
        }
        return this.studentRepository.save(student);
    }

    @Override
    public Optional<StudentResponseDto> update(StudentRequestDto student) {
        var studentOpt = this.studentRepository.getStudentByEmail(student.email());
        if (studentOpt.isEmpty()){
            throw new ErrorValidationExceptions("El estudiante no se encuentra registrado");
        }
        return this.studentRepository.save(student);
    }

    @Override
    public Boolean delete(Long id) {
        var studentOpt = this.studentRepository.getStudentById(id);
        if (studentOpt.isEmpty()){
            return false;
        }
        this.studentRepository.delete(id);
        return true;
    }
}
