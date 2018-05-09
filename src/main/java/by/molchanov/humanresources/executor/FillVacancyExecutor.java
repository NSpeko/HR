package by.molchanov.humanresources.executor;

import by.molchanov.humanresources.entity.JobVacancy;
import by.molchanov.humanresources.exception.CustomExecutorException;

import java.util.List;

public interface FillVacancyExecutor {
    List<JobVacancy> fillVacancy(String userRole) throws CustomExecutorException;
}
