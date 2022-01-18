package com.example.demo.model.exceptions;

public class EmailAlreadyExistException extends Throwable{
    public EmailAlreadyExistException(String message){
        super(message);
    }
}
