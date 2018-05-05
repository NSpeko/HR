package by.molchanov.humanresources.command.broker;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.exception.CustomBrokerException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.AuthenticationExecutor;
import by.molchanov.humanresources.executor.FillVacancyExecutor;

public class AuthenticationCommand implements ConcreteCommand {
    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        AuthenticationExecutor authenticationExecutor = AuthenticationExecutor.getInstance();
        FillVacancyExecutor fillVacancyExecutor = FillVacancyExecutor.getInstance();
        try {
            authenticationExecutor.checkUserAccessory(requestHolder);
            fillVacancyExecutor.fillVacancy(requestHolder);
        } catch (CustomExecutorException e) {
            throw new CustomBrokerException(e);
        }
    }
}
