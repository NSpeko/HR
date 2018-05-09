package by.molchanov.humanresources.command.impl;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.entity.JobVacancy;
import by.molchanov.humanresources.exception.CustomBrokerException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.DeleteExecutor;
import by.molchanov.humanresources.executor.FillVacancyExecutor;
import by.molchanov.humanresources.executor.impl.DeleteExecutorImpl;
import by.molchanov.humanresources.executor.impl.FillVacancyExecutorImpl;

import java.util.List;

import static by.molchanov.humanresources.constant.SessionRequestAttributeNames.ROLE;
import static by.molchanov.humanresources.constant.SessionRequestAttributeNames.VACANCY_ID;
import static by.molchanov.humanresources.constant.SessionRequestAttributeNames.VACANCY_LIST;

public class DeleteCommandImpl implements ConcreteCommand {
    private static final FillVacancyExecutor FILL_VACANCY_EXECUTOR = FillVacancyExecutorImpl.getInstance();
    private static final DeleteExecutor DELETE_EXECUTOR = DeleteExecutorImpl.getInstance();

    private static final int FIRST_INDEX = 0;

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        List<JobVacancy> vacancies;
        String vacancyId = requestHolder.getSingleRequestParameter(FIRST_INDEX, VACANCY_ID);
        String userRole = (String) requestHolder.getSessionAttribute(ROLE);
        try {
            DELETE_EXECUTOR.deleteVacancy(vacancyId);
            vacancies = FILL_VACANCY_EXECUTOR.fillVacancy(userRole);
        } catch (CustomExecutorException e) {
            throw new CustomBrokerException(e);
        }
        requestHolder.addRequestAttribute(VACANCY_LIST, vacancies);
    }
}
