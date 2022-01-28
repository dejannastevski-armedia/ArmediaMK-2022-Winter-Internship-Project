package first.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidAnswerException extends Exception
{
    public InvalidAnswerException()
    {
    }

    public InvalidAnswerException(String message)
    {
        super(message);
    }

    public InvalidAnswerException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public InvalidAnswerException(Throwable cause)
    {
        super(cause);
    }
}
