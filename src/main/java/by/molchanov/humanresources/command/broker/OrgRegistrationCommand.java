package by.molchanov.humanresources.command.broker;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.exception.CustomBrokerException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.FillVacancyExecutor;
import by.molchanov.humanresources.executor.RegistrationExecutor;

public class OrgRegistrationCommand implements ConcreteCommand {
    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        RegistrationExecutor registrationExecutor = new RegistrationExecutor();
        FillVacancyExecutor fillVacancyExecutor = new FillVacancyExecutor();
        try {
            fillVacancyExecutor.fillVacancy(requestHolder);
            registrationExecutor.orgSignUp(requestHolder);
        } catch (CustomExecutorException e) {
            throw new CustomBrokerException(e);
        }
    }
}
