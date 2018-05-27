package by.molchanov.humanresources.executor.impl;

import by.molchanov.humanresources.dao.JobRequestDAO;
import by.molchanov.humanresources.dao.JobVacancyDAO;
import by.molchanov.humanresources.entity.JobRequest;
import by.molchanov.humanresources.entity.JobVacancy;
import by.molchanov.humanresources.exception.CustomDAOException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.ConfirmExecutor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by Yauhen Malchanau on 27.05.2018.
 */
public class ConfirmExecutorImplTest {

    @InjectMocks
    private ConfirmExecutor executor = ConfirmExecutorImpl.getInstance();

    @Mock
    private JobVacancyDAO jobVacancyDAO;

    @Mock
    private JobRequestDAO jobRequestDAO;

    private JobVacancy jobVacancy = new JobVacancy();
    private JobRequest jobRequest = new JobRequest();

    @BeforeTest
    public void before() {
        initMocks(this);
    }

    @AfterTest
    public void after() {
        reset(jobRequestDAO, jobVacancyDAO);
    }

    @Test
    public void confirmVacancyTest() throws CustomDAOException, CustomExecutorException {
        when(jobVacancyDAO.findById(anyInt()))
                .thenReturn(jobVacancy);
        doNothing()
                .when(jobVacancyDAO).update(any(JobVacancy.class));

        executor.confirmVacancy("1");

        verify(jobVacancyDAO).findById(anyInt());
        verify(jobVacancyDAO).update(any(JobVacancy.class));
        verifyNoMoreInteractions(jobVacancyDAO);
    }

    @Test(expectedExceptions = CustomExecutorException.class)
    public void shouldThrowExceptionWhenConfirmVacancyTest() throws CustomDAOException, CustomExecutorException {
        when(jobVacancyDAO.findById(anyInt()))
                .thenThrow(new CustomDAOException());

        executor.confirmVacancy("1");
    }

    @Test
    public void confirmRequest() throws CustomDAOException, CustomExecutorException {
        when(jobRequestDAO.findById(anyInt()))
                .thenReturn(jobRequest);
        doNothing()
                .when(jobRequestDAO).update(any(JobRequest.class));

        executor.confirmRequest("1");

        verify(jobRequestDAO).findById(anyInt());
        verify(jobRequestDAO).update(any(JobRequest.class));
        verifyNoMoreInteractions(jobRequestDAO);
    }

    @Test(expectedExceptions = CustomExecutorException.class)
    public void shouldThrowExceptionWhenConfirmRequestTest() throws CustomDAOException, CustomExecutorException {
        when(jobRequestDAO.findById(anyInt()))
                .thenThrow(new CustomDAOException());

        executor.confirmRequest("1");
    }
}
