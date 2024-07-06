package com.microservice.auth.persistence.repository;


import com.microservice.auth.persistence.entities.RoleEntity;

import java.util.List;
import java.util.Optional;

public interface IRoleRepository {
    List<RoleEntity> findAll();

    List<RoleEntity> findAllByEnum(List<String> roleList);

    Optional<RoleEntity> findById(Long id);


    RoleEntity save(RoleEntity newRole);

    Iterable<RoleEntity> saveAll(Iterable<RoleEntity> roles);

    void delete(Long id);
}
