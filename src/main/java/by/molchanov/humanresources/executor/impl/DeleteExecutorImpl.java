package by.molchanov.humanresources.executor.impl;

import by.molchanov.humanresources.dao.JobRequestDAO;
import by.molchanov.humanresources.dao.JobVacancyDAO;
import by.molchanov.humanresources.dao.impl.JobRequestDAOImpl;
import by.molchanov.humanresources.dao.impl.JobVacancyDAOImpl;
import by.molchanov.humanresources.entity.JobRequest;
import by.molchanov.humanresources.entity.JobVacancy;
import by.molchanov.humanresources.exception.CustomDAOException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.DeleteExecutor;

public class DeleteExecutorImpl implements DeleteExecutor {
    private static final DeleteExecutorImpl DELETE_EXECUTOR = new DeleteExecutorImpl();

    private static final JobVacancyDAO JOB_VACANCY_DAO = JobVacancyDAOImpl.getInstance();
    private static final JobRequestDAO JOB_REQUEST_DAO = JobRequestDAOImpl.getInstance();

    private DeleteExecutorImpl() {}

    public static DeleteExecutorImpl getInstance() {
        return DELETE_EXECUTOR;
    }

    @Override
    public void deleteVacancy(String vacancyId) throws CustomExecutorException {
        JobVacancy jobVacancy = new JobVacancy();
        int id = Integer.parseInt(vacancyId);
        jobVacancy.setId(id);
        try {
            JOB_VACANCY_DAO.delete(jobVacancy);
        } catch (CustomDAOException e) {
            throw new CustomExecutorException(e);
        }
    }

    @Override
    public void deleteRequest(String requestId) throws CustomExecutorException {
        JobRequest jobRequest = new JobRequest();
        int id = Integer.parseInt(requestId);
        jobRequest.setId(id);
        try {
            JOB_REQUEST_DAO.delete(jobRequest);
        } catch (CustomDAOException e) {
            throw new CustomExecutorException(e);
        }
    }
}
