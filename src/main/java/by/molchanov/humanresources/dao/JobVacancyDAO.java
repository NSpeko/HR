package by.molchanov.humanresources.dao;

import by.molchanov.humanresources.entity.JobVacancy;
import by.molchanov.humanresources.entity.JobVacancyStatusType;
import by.molchanov.humanresources.exception.CustomDAOException;

import java.util.List;

public interface JobVacancyDAO extends OverallDAO<JobVacancy> {
    List<JobVacancy> findOpenVacancyAndOrganizationInfo(JobVacancyStatusType jobVacancyStatusType) throws CustomDAOException;
}
