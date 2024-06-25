package com.microservice.course.exception;

public class ErrorValidationExceptions extends RuntimeException{
    public ErrorValidationExceptions(String msj){
        super(msj);
    }
}
