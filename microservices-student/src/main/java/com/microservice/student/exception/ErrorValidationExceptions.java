package com.microservice.student.exception;

public class ErrorValidationExceptions extends RuntimeException{
    public ErrorValidationExceptions(String msj){
        super(msj);
    }
}
