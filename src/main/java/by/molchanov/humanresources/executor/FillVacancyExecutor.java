package by.molchanov.humanresources.executor;

import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.dao.JobVacancyDAO;
import by.molchanov.humanresources.entity.JobVacancy;
import by.molchanov.humanresources.exception.CustomDAOException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

import static by.molchanov.humanresources.constant.AttributeNames.INFO_MESSAGE;
import static by.molchanov.humanresources.constant.AttributeNames.VACANCY_LIST;

public class FillVacancyExecutor {
    private static final Logger LOGGER = LogManager.getLogger();

    public void fillVacancy(RequestHolder requestHolder) throws CustomExecutorException {
        JobVacancyDAO jobVacancyDAO = new JobVacancyDAO();
        try {
            List<JobVacancy> vacancies = jobVacancyDAO.findAll();
            requestHolder.addRequestAttribute(VACANCY_LIST, vacancies);
        } catch (CustomDAOException e) {
            requestHolder.addRequestAttribute(INFO_MESSAGE, "7");
            throw new CustomExecutorException(e);
        }
    }
}
