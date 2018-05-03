package by.molchanov.humanresources.executor;

import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.dao.JobVacancyDAO;
import by.molchanov.humanresources.entity.JobVacancy;
import by.molchanov.humanresources.exception.CustomDAOException;
import by.molchanov.humanresources.exception.CustomExecutorException;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static by.molchanov.humanresources.constant.AttributeNames.*;
import static by.molchanov.humanresources.constant.AttributeNames.INFO_MESSAGE;

public class FilterExecutor {
    private static final String INCREASE = "increase";
    private static final String DECREASE = "decrease";

    public void filterVacancy(RequestHolder requestHolder) throws CustomExecutorException {
        boolean sortTypeFlag;
        String sortColumn = requestHolder.getSingleRequestParameter(0, SORT_COL);
        String sortType = requestHolder.getSingleRequestParameter(0, SORT_TYPE);
        String searchField = requestHolder.getSingleRequestParameter(0, SEARCH_FIELD);
        if (DECREASE.equals(sortType)) {
            sortTypeFlag = true;
        } else if (INCREASE.equals(sortType)) {
            sortTypeFlag = false;
        } else {
            throw new CustomExecutorException("Unknown sorting type!");
        }
        ColumnForSortingType sortingType = ColumnForSortingType.valueOf(sortColumn.toUpperCase());
        List<JobVacancy> vacancies = selectVacancyRecord(requestHolder);
        switch (sortingType) {
            case DATE:
//                vacancySorter(, vacancies, sortTypeFlag);
                break;
            case NAME:
//                vacancySorter(, vacancies, sortTypeFlag);
                break;
            case EMPTY:
//                vacancySorter(, vacancies, sortTypeFlag);
                break;
            case ORGANIZATION:
//                vacancySorter(, vacancies, sortTypeFlag);
                break;
            default:
                    throw new CustomExecutorException("Unknown sorting column!");
        }
    }

    private List<JobVacancy> selectVacancyRecord(RequestHolder requestHolder) throws CustomExecutorException {
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
        if(sortTypeFlag) {
            Collections.reverse(vacancies);
        }
    }
}
