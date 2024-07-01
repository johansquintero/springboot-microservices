package com.microservice.gateway.persistence.repository;


import com.microservice.gateway.persistence.entities.UserEntity;

import java.util.Optional;

public interface IUserRepository {

    Optional<UserEntity> findById(Long id);

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> save(UserEntity newUser);

    Iterable<UserEntity> saveAll(Iterable<UserEntity> users);

    void delete(Long id);
}
