package by.molchanov.humanresources.command.impl;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.controller.RequestHolder;

import by.molchanov.humanresources.exception.CustomBrokerException;

import static by.molchanov.humanresources.constant.SessionRequestAttributeNames.LOCALE;

public class EstablishBelorussianLocaleCommandImpl implements ConcreteCommand {
    private static final ConcreteCommand FILL_VACANCY_COMMAND = FillVacancyCommandImpl.getInstance();
    private static final String BELORUSSIAN_LOCALE = "be_BY";

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        FILL_VACANCY_COMMAND.execute(requestHolder);
        requestHolder.addSessionAttribute(LOCALE, BELORUSSIAN_LOCALE);
    }
}