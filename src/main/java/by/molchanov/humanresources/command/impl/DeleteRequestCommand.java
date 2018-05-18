package by.molchanov.humanresources.command.impl;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.exception.CustomBrokerException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.DeleteExecutor;
import by.molchanov.humanresources.executor.impl.DeleteExecutorImpl;

import static by.molchanov.humanresources.command.SessionRequestAttributeName.REQUEST_ID;

public class DeleteRequestCommand implements ConcreteCommand {
    private static final DeleteRequestCommand DELETE_REQUEST_COMMAND = new DeleteRequestCommand();
    private static final ConcreteCommand FILL_VACANCY_COMMAND = FillContentCommand.getInstance();
    private static final DeleteExecutor DELETE_EXECUTOR = DeleteExecutorImpl.getInstance();

    private static final int FIRST_INDEX = 0;

    private DeleteRequestCommand() {

    }

    public static DeleteRequestCommand getInstance() {
        return DELETE_REQUEST_COMMAND;
    }

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        String requestId = requestHolder.getSingleRequestParameter(FIRST_INDEX, REQUEST_ID);
        try {
            DELETE_EXECUTOR.deleteVacancy(requestId);
            FILL_VACANCY_COMMAND.execute(requestHolder);
        } catch (CustomExecutorException e) {
            throw new CustomBrokerException(e);
        }
    }
}
