package by.molchanov.humanresources.executor;

import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.dao.JobVacancyDAO;
import by.molchanov.humanresources.entity.JobVacancy;
import by.molchanov.humanresources.exception.CustomDAOException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import java.util.List;

import static by.molchanov.humanresources.constant.PropertyVariablesNames.FILL_VACANCY_ERROR;
import static by.molchanov.humanresources.constant.SessionRequestAttributeNames.INFO_MESSAGE;
import static by.molchanov.humanresources.constant.SessionRequestAttributeNames.VACANCY_LIST;

public class FillVacancyExecutor {
    public void fillVacancy(RequestHolder requestHolder) throws CustomExecutorException {
        JobVacancyDAO jobVacancyDAO = new JobVacancyDAO();
        try {
            List<JobVacancy> vacancies = jobVacancyDAO.findAll();
            requestHolder.addRequestAttribute(VACANCY_LIST, vacancies);
        } catch (CustomDAOException e) {
            requestHolder.addRequestAttribute(INFO_MESSAGE, FILL_VACANCY_ERROR);
            throw new CustomExecutorException(e);
        }
    }
}
