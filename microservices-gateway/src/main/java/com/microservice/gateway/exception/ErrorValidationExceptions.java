package com.microservice.gateway.exception;

public class ErrorValidationExceptions extends RuntimeException{
    public ErrorValidationExceptions(String msj){
        super(msj);
    }
}