package first.project.exceptions;

public class InvalidCreatorException extends Exception
{
    public InvalidCreatorException()
    {
        super();
    }

    public InvalidCreatorException(String message)
    {
        super(message);
    }

    public InvalidCreatorException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public InvalidCreatorException(Throwable cause)
    {
        super(cause);
    }
}
