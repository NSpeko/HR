package by.molchanov.humanresources.command.impl;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.exception.CustomBrokerException;

public class EmptyCommandImpl implements ConcreteCommand {
    private static final ConcreteCommand FILL_VACANCY_COMMAND = FillVacancyCommandImpl.getInstance();

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        FILL_VACANCY_COMMAND.execute(requestHolder);
    }
}
