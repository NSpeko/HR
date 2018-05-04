package by.molchanov.humanresources.executor;

import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.dao.JobVacancyDAO;
import by.molchanov.humanresources.entity.JobVacancy;
import by.molchanov.humanresources.exception.CustomDAOException;
import by.molchanov.humanresources.exception.CustomExecutorException;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static by.molchanov.humanresources.constant.SessionRequestAttributeNames.*;
import static by.molchanov.humanresources.entity.JobVacancy.COMPARE_BY_DATE;
import static by.molchanov.humanresources.entity.JobVacancy.COMPARE_BY_NAME;
import static by.molchanov.humanresources.entity.JobVacancy.COMPARE_BY_ORG_NAME;

public class FilterExecutor {
    private static final String INCREASE = "increase";
    private static final String DECREASE = "decrease";
    private static final String EMPTY = "empty";

    public void filterVacancy(RequestHolder requestHolder) throws CustomExecutorException {
        String sortColumn = requestHolder.getSingleRequestParameter(0, SORT_COL);
        String sortDirectionType = requestHolder.getSingleRequestParameter(0, SORT_TYPE);
        String searchField = requestHolder.getSingleRequestParameter(0, SEARCH_FIELD);
        boolean sortDirectionTypeFlag = setSortDirectionTypeFlag(sortDirectionType);
        ColumnForSortingType sortingColumnType = ColumnForSortingType.valueOf(sortColumn.toUpperCase());
        List<JobVacancy> vacancies = findVacancyRecord();
        if (!searchField.isEmpty()) {
            vacancies.removeIf(vacancy -> !vacancy.getName().toLowerCase().contains(searchField.toLowerCase()));
        }
        executeSort(sortingColumnType, vacancies, sortDirectionTypeFlag);
        requestHolder.addRequestAttribute(VACANCY_LIST, vacancies);
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
        JobVacancyDAO jobVacancyDAO = new JobVacancyDAO();
        List<JobVacancy> vacancies;
        try {
            vacancies = jobVacancyDAO.findAll();
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
