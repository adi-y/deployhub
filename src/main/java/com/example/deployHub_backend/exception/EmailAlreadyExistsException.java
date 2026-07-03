package com.example.deployHub_backend.exception;


public class EmailAlreadyExistsException extends RuntimeException{
    public EmailAlreadyExistsException(String message){
        super (message);
    }
}
