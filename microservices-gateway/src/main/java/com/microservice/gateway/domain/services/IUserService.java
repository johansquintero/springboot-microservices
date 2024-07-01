package com.microservice.gateway.domain.services;

import com.microservice.gateway.domain.dto.AuthRequestLogInDto;
import com.microservice.gateway.domain.dto.AuthRequestSignUpDto;
import com.microservice.gateway.domain.dto.AuthResponseDto;
import com.microservice.gateway.persistence.entities.UserEntity;

import java.util.Optional;

public interface IUserService {
    Optional<UserEntity> findById(Long id);

    Optional<UserEntity> findByUsername(String username);

    UserEntity save(UserEntity newUser);

    Iterable<UserEntity> saveAll(Iterable<UserEntity> users);

    void delete(Long id);

    AuthResponseDto logIn(AuthRequestLogInDto authRequestLogInDto);

    AuthResponseDto signUp(AuthRequestSignUpDto authRequestSignUpDto);
}
