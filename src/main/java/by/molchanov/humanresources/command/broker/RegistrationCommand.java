package by.molchanov.humanresources.command.broker;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.executor.FillVacancyExecutor;
import by.molchanov.humanresources.executor.RegistrationExecutor;

public class RegistrationCommand implements ConcreteCommand {
    @Override
    public void execute(RequestHolder requestHolder) {
        RegistrationExecutor registrationExecutor = new RegistrationExecutor();
        FillVacancyExecutor fillVacancyExecutor = new FillVacancyExecutor();
        registrationExecutor.userSignUp(requestHolder);
        fillVacancyExecutor.fillVacancy(requestHolder);
    }
}
