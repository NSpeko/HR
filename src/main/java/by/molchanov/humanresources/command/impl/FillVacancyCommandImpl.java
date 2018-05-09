package by.molchanov.humanresources.command.impl;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.entity.JobVacancy;
import by.molchanov.humanresources.exception.CustomBrokerException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.FillVacancyExecutor;
import by.molchanov.humanresources.executor.impl.FillVacancyExecutorImpl;

import java.util.List;

import static by.molchanov.humanresources.constant.SessionRequestAttributeNames.ROLE;
import static by.molchanov.humanresources.constant.SessionRequestAttributeNames.VACANCY_LIST;

public class FillVacancyCommandImpl implements ConcreteCommand {
    private static final FillVacancyCommandImpl FILL_VACANCY_COMMAND = new FillVacancyCommandImpl();
    private static final FillVacancyExecutor FILL_VACANCY_EXECUTOR = FillVacancyExecutorImpl.getInstance();

    private FillVacancyCommandImpl() {

    }

    public static FillVacancyCommandImpl getInstance() {
        return FILL_VACANCY_COMMAND;
    }

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        List<JobVacancy> vacancies;
        String userRole = (String) requestHolder.getSessionAttribute(ROLE);
        try {
            vacancies = FILL_VACANCY_EXECUTOR.fillVacancy(userRole);
        } catch (CustomExecutorException e) {
            throw new CustomBrokerException(e);
        }
        requestHolder.addRequestAttribute(VACANCY_LIST, vacancies);
    }
}
