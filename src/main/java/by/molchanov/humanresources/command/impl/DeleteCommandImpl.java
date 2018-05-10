package by.molchanov.humanresources.command.impl;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.exception.CustomBrokerException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.DeleteExecutor;
import by.molchanov.humanresources.executor.impl.DeleteExecutorImpl;

import static by.molchanov.humanresources.constant.SessionRequestAttributeNames.VACANCY_ID;

public class DeleteCommandImpl implements ConcreteCommand {
    private static final ConcreteCommand FILL_VACANCY_COMMAND = FillVacancyCommandImpl.getInstance();
    private static final DeleteExecutor DELETE_EXECUTOR = DeleteExecutorImpl.getInstance();

    private static final int FIRST_INDEX = 0;

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        String vacancyId = requestHolder.getSingleRequestParameter(FIRST_INDEX, VACANCY_ID);
        try {
            DELETE_EXECUTOR.deleteVacancy(vacancyId);
            FILL_VACANCY_COMMAND.execute(requestHolder);
        } catch (CustomExecutorException e) {
            throw new CustomBrokerException(e);
        }
    }
}
