package by.molchanov.humanresources.executor.impl;

import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.SendMessageExecutor;

public class SendMessageExecutorImpl implements SendMessageExecutor {
    private static final SendMessageExecutorImpl SEND_MESSAGE_EXECUTOR = new SendMessageExecutorImpl();

    private static final String SIGNATURE = "\n place for signature!!!";

    private SendMessageExecutorImpl() {

    }

    public static SendMessageExecutorImpl getInstance() {
        return SEND_MESSAGE_EXECUTOR;
    }

    @Override
    public void sendRequestAnswer(String message, String aspirantEmail) throws CustomExecutorException {
        message += SIGNATURE;
        sendMessage(message, aspirantEmail);
    }

    private void sendMessage(String message, String receiver) {

    }
}
