package com.microservice.gateway.persistence.crud;

import com.microservice.gateway.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserCrudRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findUserById(Long id);

    Optional<UserEntity> findUserByUsername(String username);

}
