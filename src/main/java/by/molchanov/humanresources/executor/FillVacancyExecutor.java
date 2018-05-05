package by.molchanov.humanresources.executor;

import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.dao.JobVacancyDAO;
import by.molchanov.humanresources.dao.impl.JobVacancyDAOImpl;
import by.molchanov.humanresources.entity.JobVacancy;
import by.molchanov.humanresources.exception.CustomDAOException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import java.util.List;

import static by.molchanov.humanresources.constant.PropertyMessageVariablesNames.FILL_VACANCY_ERROR;
import static by.molchanov.humanresources.constant.SessionRequestAttributeNames.INFO_MESSAGE;
import static by.molchanov.humanresources.constant.SessionRequestAttributeNames.VACANCY_LIST;

public class FillVacancyExecutor {
    private static final FillVacancyExecutor FILL_VACANCY_EXECUTOR = new FillVacancyExecutor();

    private static final JobVacancyDAO JOB_VACANCY_DAO = JobVacancyDAOImpl.getInstance();

    private FillVacancyExecutor() {

    }

    public static FillVacancyExecutor getInstance() {
        return FILL_VACANCY_EXECUTOR;
    }

    public void fillVacancy(RequestHolder requestHolder) throws CustomExecutorException {
        try {
            List<JobVacancy> vacancies = JOB_VACANCY_DAO.findAll();
            requestHolder.addRequestAttribute(VACANCY_LIST, vacancies);
        } catch (CustomDAOException e) {
            requestHolder.addRequestAttribute(INFO_MESSAGE, FILL_VACANCY_ERROR);
            throw new CustomExecutorException(e);
        }
    }
}
