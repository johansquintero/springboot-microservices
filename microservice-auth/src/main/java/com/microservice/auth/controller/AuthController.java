package com.microservice.auth.controller;

import com.microservice.auth.domain.dto.AuthRequestLogInDto;
import com.microservice.auth.domain.dto.AuthRequestSignUpDto;
import com.microservice.auth.domain.dto.AuthResponseDto;
import com.microservice.auth.domain.services.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping(path = "/api/auth")
public class AuthController {
    private final IUserService userService;

    @PostMapping(path = "/log-in")
    public ResponseEntity<AuthResponseDto> logIn(@RequestBody AuthRequestLogInDto authRequestLogInDto){
        return ResponseEntity.ok(this.userService.logIn(authRequestLogInDto));
    }

    @PostMapping(path = "/sign-up")
    public ResponseEntity<AuthResponseDto> signUp(@RequestBody AuthRequestSignUpDto authRequestSignUpDto){
        return ResponseEntity.ok(this.userService.signUp(authRequestSignUpDto));
    }
}
