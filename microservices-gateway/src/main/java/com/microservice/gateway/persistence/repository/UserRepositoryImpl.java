package com.microservice.gateway.persistence.repository;

import com.microservice.gateway.persistence.crud.IUserCrudRepository;
import com.microservice.gateway.persistence.entities.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@AllArgsConstructor
@Repository
public class UserRepositoryImpl implements IUserRepository{
    private final IUserCrudRepository userCrudRepository;
    @Override
    public Optional<UserEntity> findById(Long id) {
        return this.userCrudRepository.findUserById(id);
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return this.userCrudRepository.findUserByUsername(username);
    }

    @Override
    public Optional<UserEntity>  save(UserEntity newUser) {
        return Optional.of(this.userCrudRepository.save(newUser));
    }

    @Override
    public Iterable<UserEntity> saveAll(Iterable<UserEntity> users) {
        return this.userCrudRepository.saveAll(users);
    }

    @Override
    public void delete(Long id) {
        this.userCrudRepository.deleteById(id);
    }
}
