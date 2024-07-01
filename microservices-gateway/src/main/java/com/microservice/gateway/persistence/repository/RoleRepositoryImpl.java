package com.microservice.gateway.persistence.repository;

import com.microservice.gateway.persistence.crud.IRoleCrudRepository;
import com.microservice.gateway.persistence.entities.RoleEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class RoleRepositoryImpl implements IRoleRepository{
    private final IRoleCrudRepository crudRepository;

    @Transactional(readOnly = true)
    @Override
    public List<RoleEntity> findAll() {
        return this.crudRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<RoleEntity> findAllByEnum(List<String> roleList) {
        return this.crudRepository.getRolesEntitiesByRoleEnumIn(roleList);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<RoleEntity> findById(Long id) {
        return this.crudRepository.findById(id);
    }

    @Transactional
    @Override
    public RoleEntity save(RoleEntity newRole) {
        return this.crudRepository.save(newRole);
    }

    @Transactional
    @Override
    public Iterable<RoleEntity> saveAll(Iterable<RoleEntity> roles) {
        return this.crudRepository.saveAll(roles);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        this.crudRepository.deleteById(id);
    }
}
