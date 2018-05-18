package by.molchanov.humanresources.executor;

import by.molchanov.humanresources.entity.JobVacancy;
import by.molchanov.humanresources.exception.CustomExecutorException;

import java.util.List;

public interface VacancyFilterExecutor {
    List<JobVacancy> filterVacancy(String sortColumn, String sortDirectionType, String searchField, String userRole) throws CustomExecutorException;
}
