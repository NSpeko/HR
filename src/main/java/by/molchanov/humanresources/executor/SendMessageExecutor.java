package by.molchanov.humanresources.executor;

import by.molchanov.humanresources.exception.CustomExecutorException;

public interface SendMessageExecutor {
    void sendRequestAnswer(String message, String aspirantEmail) throws CustomExecutorException;
}
