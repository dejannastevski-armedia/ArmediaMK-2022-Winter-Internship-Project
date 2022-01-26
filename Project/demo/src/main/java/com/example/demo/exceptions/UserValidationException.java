package com.example.demo.exceptions;

public class UserValidationException extends Exception
{
    public UserValidationException()
    {
        super();
    }

    public UserValidationException(String message)
    {
        super(message);
    }

    public UserValidationException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public UserValidationException(Throwable cause)
    {
        super(cause);
    }
}
