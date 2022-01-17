package com.example.demo.model.exceptions;

public class InvalidArgumentException extends Throwable {

    public InvalidArgumentException(String message)
    {
        super(message);
    }
}