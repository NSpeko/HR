package by.molchanov.humanresources.executor.impl;

import by.molchanov.humanresources.dao.JobVacancyDAO;
import by.molchanov.humanresources.dao.impl.JobVacancyDAOImpl;
import by.molchanov.humanresources.entity.JobVacancy;
import by.molchanov.humanresources.exception.CustomDAOException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.FillVacancyExecutor;

import java.util.List;

public class FillVacancyExecutorImpl implements FillVacancyExecutor {
    private static final FillVacancyExecutorImpl FILL_VACANCY_EXECUTOR = new FillVacancyExecutorImpl();

    private static final JobVacancyDAO JOB_VACANCY_DAO = JobVacancyDAOImpl.getInstance();

    private FillVacancyExecutorImpl() {

    }

    public static FillVacancyExecutorImpl getInstance() {
        return FILL_VACANCY_EXECUTOR;
    }

    @Override
    public List<JobVacancy> fillVacancy() throws CustomExecutorException {
        List<JobVacancy> vacancies;
        try {
            vacancies = JOB_VACANCY_DAO.findAll();
        } catch (CustomDAOException e) {
            throw new CustomExecutorException(e);
        }
        return vacancies;
    }
}
