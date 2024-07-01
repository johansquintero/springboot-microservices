package com.microservice.gateway.domain.services;

import com.microservice.gateway.domain.dto.AuthRequestLogInDto;
import com.microservice.gateway.domain.dto.AuthRequestSignUpDto;
import com.microservice.gateway.domain.dto.AuthResponseDto;
import com.microservice.gateway.exception.ErrorAlertMessages;
import com.microservice.gateway.persistence.entities.RoleEntity;
import com.microservice.gateway.persistence.entities.UserEntity;
import com.microservice.gateway.persistence.repository.IRoleRepository;
import com.microservice.gateway.persistence.repository.IUserRepository;
import com.microservice.gateway.utils.JwtUtils;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService{
    private final PasswordEncoder passwordEncoder;
    private final IUserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;
    private final IRoleRepository roleRepository;


    @Override
    public Optional<UserEntity> findById(Long id) {
        return Optional.of(userRepository
                .findById(id)
                .orElseThrow(() -> new ValidationException(ErrorAlertMessages.USER_NOT_EXISTS_MESSAGE)));
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return Optional.of(userRepository
                .findByUsername(username)
                .orElseThrow(() -> new ValidationException(ErrorAlertMessages.USER_NOT_EXISTS_MESSAGE)));
    }

    @Override
    public UserEntity save(UserEntity newUser) {
        var x = this.userRepository.
                findByUsername(newUser.getUsername());
        if (x.isPresent()) {
            throw new ValidationException(ErrorAlertMessages.USER_ALREADY_EXISTS_MESSAGE);
        }
        newUser.setPassword(this.passwordEncoder.encode(newUser.getPassword()));
        return this.userRepository.save(newUser).orElse(new UserEntity());
    }

    @Override
    public Iterable<UserEntity> saveAll(Iterable<UserEntity> users) {
        users.forEach(userEntity -> {
                    var x = this.userRepository.
                            findByUsername(userEntity.getUsername());
                    if (x.isPresent()) {
                        throw new ValidationException(ErrorAlertMessages.USER_ALREADY_EXISTS_MESSAGE);
                    }
                    userEntity.setPassword(this.passwordEncoder.encode(userEntity.getPassword()));
                }
        );
        return userRepository.saveAll(users);
    }

    @Override
    public void delete(Long id) {
        Optional<UserEntity> userOpt = this.findById(id);
        if (userOpt.isEmpty()){
            throw new ValidationException(ErrorAlertMessages.USER_NOT_EXISTS_MESSAGE);
        }
        this.userRepository.delete(id);

    }

    @Override
    public AuthResponseDto logIn(AuthRequestLogInDto authRequestLogInDto) {
        String username = authRequestLogInDto.username();
        String password = authRequestLogInDto.password();
        Authentication authentication = this.authenticate(username,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = this.jwtUtils.createToken(authentication);
        return new AuthResponseDto(username,"User logged sucessfully",accessToken,true);
    }
    public Authentication authenticate(String username, String password){
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
        if (userDetails==null){
            throw new ValidationException("Invalid username");
        }
        if (!this.passwordEncoder.matches(password,userDetails.getPassword())){
            throw new ValidationException("Incorrect password");
        }
        return new UsernamePasswordAuthenticationToken(username,userDetails.getPassword(),userDetails.getAuthorities());
    }

    @Override
    public AuthResponseDto signUp(AuthRequestSignUpDto authRequestSignUpDto) {
        Optional<UserEntity> userOpt = this.findByUsername(authRequestSignUpDto.username());
        List<String> roleList = authRequestSignUpDto.authRolesRequestDto().rolesListName();
        Set<RoleEntity> roleEntitySet = new HashSet<>(this.roleRepository.findAllByEnum(roleList));

        if (userOpt.isPresent()){
            throw new ValidationException(ErrorAlertMessages.USER_ALREADY_EXISTS_MESSAGE);
        }
        if (roleEntitySet.isEmpty()){
            throw new ValidationException(ErrorAlertMessages.ROLES_NOT_EXIST_MESSAGE);
        }
        UserEntity newUser = UserEntity.builder()
                .roles(roleEntitySet)
                .password(this.passwordEncoder.encode(authRequestSignUpDto.password()))
                .accountNoExpired(true)
                .credentialNoExpired(true)
                .isEnabled(true)
                .accountNoLocked(true)
                .username(authRequestSignUpDto.username())
                .build();

        UserEntity userCreated = this.userRepository.save(newUser).orElse(new UserEntity());
        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();

        userCreated.getRoles()
                .forEach(
                        roleEntity -> grantedAuthorities.add(
                                new SimpleGrantedAuthority("ROLE".concat(roleEntity.getRoleEnum().name())))
                );
        userCreated.getRoles()
                .stream()
                .flatMap(roleEntity -> roleEntity.getPermissions().stream())
                .forEach(
                        permissionEntity -> grantedAuthorities.add(new SimpleGrantedAuthority(permissionEntity.getName()))
                );

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userCreated.getUsername(),
                userCreated.getPassword(),
                grantedAuthorities
        );

        String accessToken = this.jwtUtils.createToken(authentication);
        return new AuthResponseDto(
                userCreated.getUsername(),
                "User created successfully",
                accessToken,
                true);
    }
}
