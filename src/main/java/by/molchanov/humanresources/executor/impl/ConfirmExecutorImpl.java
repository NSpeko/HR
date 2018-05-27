package by.molchanov.humanresources.executor.impl;

import by.molchanov.humanresources.dao.JobRequestDAO;
import by.molchanov.humanresources.dao.JobVacancyDAO;
import by.molchanov.humanresources.dao.impl.JobRequestDAOImpl;
import by.molchanov.humanresources.dao.impl.JobVacancyDAOImpl;
import by.molchanov.humanresources.entity.JobRequest;
import by.molchanov.humanresources.entity.JobRequestStatusType;
import by.molchanov.humanresources.entity.JobVacancy;
import by.molchanov.humanresources.entity.JobVacancyStatusType;
import by.molchanov.humanresources.exception.CustomDAOException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.ConfirmExecutor;

/**
 * Class {@link ConfirmExecutorImpl} used for confirm records.
 *
 * @author MolcanovVladislav
 * @see ConfirmExecutor
 */
public class ConfirmExecutorImpl implements ConfirmExecutor {
    private static final ConfirmExecutorImpl CONFIRM_EXECUTOR = new ConfirmExecutorImpl();

    private JobVacancyDAO jobVacancyDAO = JobVacancyDAOImpl.getInstance();
    private JobRequestDAO jobRequestDAO = JobRequestDAOImpl.getInstance();

    public static ConfirmExecutorImpl getInstance() {
        return CONFIRM_EXECUTOR;
    }

    private ConfirmExecutorImpl() {

    }

    @Override
    public void confirmVacancy(String vacancyId) throws CustomExecutorException {
        int id = Integer.parseInt(vacancyId);
        try {
            JobVacancy jobVacancy = jobVacancyDAO.findById(id);
            jobVacancy.setStatus(JobVacancyStatusType.OPEN);
            jobVacancyDAO.update(jobVacancy);
        } catch (CustomDAOException e) {
            throw new CustomExecutorException(e);
        }
    }

    @Override
    public void confirmRequest(String requestId) throws CustomExecutorException {
        int id = Integer.parseInt(requestId);
        try {
            JobRequest jobRequest = jobRequestDAO.findById(id);
            jobRequest.setStatus(JobRequestStatusType.ADDED);
            jobRequestDAO.update(jobRequest);
        } catch (CustomDAOException e) {
            throw new CustomExecutorException(e);
        }
    }
}
