package com.microservice.auth.exception;

public class ErrorValidationExceptions extends RuntimeException{
    public ErrorValidationExceptions(String msj){
        super(msj);
    }
}