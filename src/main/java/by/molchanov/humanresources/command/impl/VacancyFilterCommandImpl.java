package by.molchanov.humanresources.command.impl;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.entity.JobVacancy;
import by.molchanov.humanresources.exception.CustomBrokerException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.VacancyFilterExecutor;
import by.molchanov.humanresources.executor.impl.VacancyFilterExecutorImpl;

import java.util.List;

import static by.molchanov.humanresources.constant.SessionRequestAttributeNames.*;

public class VacancyFilterCommandImpl implements ConcreteCommand {
    private static final VacancyFilterExecutor VACANCY_FILTER_EXECUTOR = VacancyFilterExecutorImpl.getInstance();
    private static final int FIRST_INDEX = 0;

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        String sortColumn = requestHolder.getSingleRequestParameter(FIRST_INDEX, SORT_COL);
        String sortDirectionType = requestHolder.getSingleRequestParameter(FIRST_INDEX, SORT_TYPE);
        String searchField = requestHolder.getSingleRequestParameter(FIRST_INDEX, SEARCH_FIELD);
        List<JobVacancy> vacancies;
        try {
            vacancies = VACANCY_FILTER_EXECUTOR.filterVacancy(sortColumn, sortDirectionType, searchField);
        } catch (CustomExecutorException e) {
            throw new CustomBrokerException(e);
        }
        requestHolder.addRequestAttribute(VACANCY_LIST, vacancies);
    }
}
