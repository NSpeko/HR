package by.molchanov.humanresources.command.impl;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.entity.JobVacancy;
import by.molchanov.humanresources.exception.CustomBrokerException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.FillVacancyExecutor;
import by.molchanov.humanresources.executor.LogOutExecutor;
import by.molchanov.humanresources.executor.impl.FillVacancyExecutorImpl;
import by.molchanov.humanresources.executor.impl.LogOutExecutorImpl;

import java.util.List;

import static by.molchanov.humanresources.constant.SessionRequestAttributeNames.ROLE;
import static by.molchanov.humanresources.constant.SessionRequestAttributeNames.VACANCY_LIST;

public class LogOutCommandImpl implements ConcreteCommand {
    private static final LogOutExecutor LOG_OUT_EXECUTOR = LogOutExecutorImpl.getInstance();
    private static final FillVacancyExecutor FILL_VACANCY_EXECUTOR = FillVacancyExecutorImpl.getInstance();

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        List<JobVacancy> vacancies;
        List<String> attributeForDelete;
        String userRole = "";
        try {
            attributeForDelete = LOG_OUT_EXECUTOR.logOut();
            vacancies = FILL_VACANCY_EXECUTOR.fillVacancy(userRole);
        } catch (CustomExecutorException e) {
            throw new CustomBrokerException(e);
        }
        requestHolder.removeSessionAttribute(attributeForDelete.toArray(new String[attributeForDelete.size()]));
        requestHolder.addRequestAttribute(VACANCY_LIST, vacancies);
    }
}
