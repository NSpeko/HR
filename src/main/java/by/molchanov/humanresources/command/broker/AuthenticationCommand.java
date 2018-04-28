package by.molchanov.humanresources.command.broker;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.executor.AuthenticationExecutor;
import by.molchanov.humanresources.executor.FillVacancyExecutor;

public class AuthenticationCommand implements ConcreteCommand {
    @Override
    public void execute(RequestHolder requestHolder) {
        AuthenticationExecutor authenticationExecutor = new AuthenticationExecutor();
        FillVacancyExecutor fillVacancyExecutor = new FillVacancyExecutor();
        authenticationExecutor.checkUserAccessory(requestHolder);
        fillVacancyExecutor.fillVacancy(requestHolder);
    }
}
