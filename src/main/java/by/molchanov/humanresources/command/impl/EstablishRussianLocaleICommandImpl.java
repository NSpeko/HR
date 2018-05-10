package by.molchanov.humanresources.command.impl;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.exception.CustomBrokerException;
import by.molchanov.humanresources.executor.FillContentExecutor;
import by.molchanov.humanresources.executor.impl.FillContentExecutorImpl;

import static by.molchanov.humanresources.constant.SessionRequestAttributeNames.LOCALE;

public class EstablishRussianLocaleICommandImpl implements ConcreteCommand {
    private static final FillContentExecutor FILL_VACANCY_EXECUTOR = FillContentExecutorImpl.getInstance();
    private static final ConcreteCommand FILL_VACANCY_COMMAND = FillVacancyCommandImpl.getInstance();
    private static final String RUSSIAN_LOCALE = "ru_RU";

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        FILL_VACANCY_COMMAND.execute(requestHolder);
        requestHolder.addSessionAttribute(LOCALE, RUSSIAN_LOCALE);
    }
}
