package by.molchanov.humanresources.exception;

public class CustomExecutorException extends Exception {
    public CustomExecutorException() {
    }

    public CustomExecutorException(String message) {
        super(message);
    }

    public CustomExecutorException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomExecutorException(Throwable cause) {
        super(cause);
    }

    public CustomExecutorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
