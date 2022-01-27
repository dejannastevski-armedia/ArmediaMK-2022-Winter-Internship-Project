package first.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
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