package com.microservice.gateway.exception.security;

public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException(String msj){
        super(msj);
    }
}