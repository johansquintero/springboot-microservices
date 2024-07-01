package com.microservice.gateway.domain.services;

import com.microservice.gateway.exception.ErrorAlertMessages;
import com.microservice.gateway.persistence.entities.UserEntity;
import com.microservice.gateway.persistence.repository.IUserRepository;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = this.userRepository
                .findByUsername(username)
                .orElseThrow(()->new ValidationException(ErrorAlertMessages.USER_NOT_EXISTS_MESSAGE));
        List<SimpleGrantedAuthority> grantedAuthorityList = new ArrayList<>();
        userEntity.getRoles()
                .forEach(
                        roleEntity -> grantedAuthorityList
                                .add(new SimpleGrantedAuthority("ROLE_".concat(roleEntity.getRoleEnum().name())))
                );
        userEntity.getRoles().stream()
                .flatMap(roleEntity -> roleEntity.getPermissions().stream())
                .forEach(
                        permissionEntity -> {
                            grantedAuthorityList.add(new SimpleGrantedAuthority(permissionEntity.getName()));
                        }
                );

        return new User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.isEnabled(),
                userEntity.isAccountNoExpired(),
                userEntity.isCredentialNoExpired(),
                userEntity.isAccountNoLocked(),
                grantedAuthorityList
        );
    }
}
