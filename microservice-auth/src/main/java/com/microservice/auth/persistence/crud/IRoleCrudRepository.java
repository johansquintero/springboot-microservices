package com.microservice.auth.persistence.crud;

import com.microservice.auth.persistence.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRoleCrudRepository extends JpaRepository<RoleEntity,Long> {
    List<RoleEntity> getRolesEntitiesByRoleEnumIn(List<String> roleNames);
}
