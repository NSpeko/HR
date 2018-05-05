package by.molchanov.humanresources.executor.impl;

import by.molchanov.humanresources.dao.JobVacancyDAO;
import by.molchanov.humanresources.dao.impl.JobVacancyDAOImpl;
import by.molchanov.humanresources.entity.JobVacancy;
import by.molchanov.humanresources.exception.CustomDAOException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.ColumnForSortingType;
import by.molchanov.humanresources.executor.VacancyFilterExecutor;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static by.molchanov.humanresources.entity.JobVacancy.COMPARE_BY_DATE;
import static by.molchanov.humanresources.entity.JobVacancy.COMPARE_BY_NAME;
import static by.molchanov.humanresources.entity.JobVacancy.COMPARE_BY_ORG_NAME;

public class VacancyFilterExecutorImpl implements VacancyFilterExecutor {
    private static final VacancyFilterExecutorImpl VACANCY_FILTER_EXECUTOR = new VacancyFilterExecutorImpl();

    private static final JobVacancyDAO JOB_VACANCY_DAO = JobVacancyDAOImpl.getInstance();

    private static final String INCREASE = "increase";
    private static final String DECREASE = "decrease";
    private static final String EMPTY = "empty";

    private VacancyFilterExecutorImpl() {

    }

    public static VacancyFilterExecutorImpl getInstance() {
        return VACANCY_FILTER_EXECUTOR;
    }

    @Override
    public List<JobVacancy> filterVacancy(String sortColumn, String sortDirectionType, String searchField) throws CustomExecutorException {
        boolean sortDirectionTypeFlag = setSortDirectionTypeFlag(sortDirectionType);
        ColumnForSortingType sortingColumnType = ColumnForSortingType.valueOf(sortColumn.toUpperCase());
        List<JobVacancy> vacancies = findVacancyRecord();
        if (!searchField.isEmpty()) {
            vacancies.removeIf(vacancy -> !vacancy.getName().toLowerCase().contains(searchField.toLowerCase()));
        }
        executeSort(sortingColumnType, vacancies, sortDirectionTypeFlag);
        return vacancies;
    }

    private boolean setSortDirectionTypeFlag(String sortDirectionType) throws CustomExecutorException {
        boolean result;
        switch (sortDirectionType) {
            case DECREASE:
                result = true;
                break;
            case INCREASE:
                result = false;
                break;
            case EMPTY:
                result = false;
                break;
            default:
                throw new CustomExecutorException("Unknown sorting type!");
        }
        return result;
    }

    private void executeSort(ColumnForSortingType sortingColumnType, List<JobVacancy> vacancies, boolean sortDirectionTypeFlag) throws CustomExecutorException {
        switch (sortingColumnType) {
            case SORT_BY_DATE:
                vacancySorter(COMPARE_BY_DATE, vacancies, sortDirectionTypeFlag);
                break;
            case SORT_BY_NAME:
                vacancySorter(COMPARE_BY_NAME, vacancies, sortDirectionTypeFlag);
                break;
            case SORT_BY_EMPTY_COLUMN:
                break;
            case SORT_BY_ORGANIZATION:
                vacancySorter(COMPARE_BY_ORG_NAME, vacancies, sortDirectionTypeFlag);
                break;
            default:
                throw new CustomExecutorException("Unknown sorting column!");
        }
    }

    private List<JobVacancy> findVacancyRecord() throws CustomExecutorException {
        List<JobVacancy> vacancies;
        try {
            vacancies = JOB_VACANCY_DAO.findAll();
        } catch (CustomDAOException e) {
            throw new CustomExecutorException(e);
        }
        return vacancies;
    }

    private void vacancySorter(Comparator<JobVacancy> comparator, List<JobVacancy> vacancies, boolean sortTypeFlag) {
        vacancies.sort(comparator);
        if (sortTypeFlag) {
            Collections.reverse(vacancies);
        }
    }
}
