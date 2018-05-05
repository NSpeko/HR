package by.molchanov.humanresources.command.impl;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.entity.JobVacancy;
import by.molchanov.humanresources.exception.CustomBrokerException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.FillVacancyExecutor;
import by.molchanov.humanresources.executor.impl.FillVacancyExecutorImpl;

import java.util.List;

import static by.molchanov.humanresources.constant.SessionRequestAttributeNames.VACANCY_LIST;

public class EmptyCommandImpl implements ConcreteCommand {
    private static final FillVacancyExecutor FILL_VACANCY_EXECUTOR = FillVacancyExecutorImpl.getInstance();

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        List<JobVacancy> vacancies;
        try {
            vacancies = FILL_VACANCY_EXECUTOR.fillVacancy();
        } catch (CustomExecutorException e) {
            throw new CustomBrokerException(e);
        }
        requestHolder.addRequestAttribute(VACANCY_LIST, vacancies);
    }
}
