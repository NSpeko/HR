package by.molchanov.humanresources.command.impl;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.exception.CustomBrokerException;
import by.molchanov.humanresources.executor.LogOutExecutor;
import by.molchanov.humanresources.executor.impl.LogOutExecutorImpl;

import java.util.List;

public class LogOutCommandImpl implements ConcreteCommand {
    private static final LogOutExecutor LOG_OUT_EXECUTOR = LogOutExecutorImpl.getInstance();
    private static final ConcreteCommand FILL_VACANCY_COMMAND = FillVacancyCommandImpl.getInstance();

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        List<String> attributeForDelete;
        attributeForDelete = LOG_OUT_EXECUTOR.logOut();
        FILL_VACANCY_COMMAND.execute(requestHolder);
        requestHolder.removeSessionAttribute(attributeForDelete.toArray(new String[attributeForDelete.size()]));
    }
}
