package com.microservice.gateway.persistence.crud;

import com.microservice.gateway.persistence.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IRoleCrudRepository extends JpaRepository<RoleEntity,Long> {
    List<RoleEntity> getRolesEntitiesByRoleEnumIn(List<String> roleNames);
}
