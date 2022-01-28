package first.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AddAnswerException extends Exception
{
    public AddAnswerException()
    {
    }

    public AddAnswerException(String message)
    {
        super(message);
    }

    public AddAnswerException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public AddAnswerException(Throwable cause)
    {
        super(cause);
    }
}
