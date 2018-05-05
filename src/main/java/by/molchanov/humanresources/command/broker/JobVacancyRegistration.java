package by.molchanov.humanresources.command.broker;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.exception.CustomBrokerException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.FillVacancyExecutor;
import by.molchanov.humanresources.executor.RegistrationExecutor;

public class JobVacancyRegistration implements ConcreteCommand {
    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        RegistrationExecutor registrationExecutor = RegistrationExecutor.getInstance();
        FillVacancyExecutor fillVacancyExecutor = FillVacancyExecutor.getInstance();
        try {
            registrationExecutor.vacancySignUp(requestHolder);
            fillVacancyExecutor.fillVacancy(requestHolder);
        } catch (CustomExecutorException e) {
            throw new CustomBrokerException(e);
        }
    }
}
