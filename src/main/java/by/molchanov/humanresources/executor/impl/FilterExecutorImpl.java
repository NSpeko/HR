package by.molchanov.humanresources.executor.impl;

import by.molchanov.humanresources.dto.FilterDataDTO;
import by.molchanov.humanresources.entity.JobRequest;
import by.molchanov.humanresources.entity.JobVacancy;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.ColumnForSortingType;
import by.molchanov.humanresources.executor.FillContentExecutor;
import by.molchanov.humanresources.executor.FilterExecutor;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static by.molchanov.humanresources.entity.JobVacancy.COMPARE_BY_NAME;
import static by.molchanov.humanresources.entity.JobVacancy.COMPARE_BY_ORG_NAME;

import static by.molchanov.humanresources.entity.JobRequest.COMPARE_BY_USER_NAME;
import static by.molchanov.humanresources.entity.JobRequest.COMPARE_BY_VAC_NAME;

/**
 * Class {@link FilterExecutorImpl} is used for searching and filter records.
 *
 * @author Molchanov Vladislav
 * @see FilterExecutor
 */
public class FilterExecutorImpl implements FilterExecutor {
    private static final FilterExecutorImpl VACANCY_FILTER_EXECUTOR = new FilterExecutorImpl();
    private FillContentExecutor fillContentExecutor = FillContentExecutorImpl.getInstance();

    private static final String INCREASE = "increase";
    private static final String DECREASE = "decrease";
    private static final String EMPTY = "empty";

    private FilterExecutorImpl() {

    }

    public static FilterExecutorImpl getInstance() {
        return VACANCY_FILTER_EXECUTOR;
    }

    @Override
    public List<JobVacancy> filterVacancy(FilterDataDTO filterDataDTO, String userRole, int startVacancyNumber,
                                          int vacanciesQuantity) throws CustomExecutorException {
        String sortDirectionType = filterDataDTO.getSortDirectionType();
        String sortColumn = filterDataDTO.getSortColumn();
        String searchField = filterDataDTO.getSearchField();
        boolean sortDirectionTypeFlag = setSortDirectionTypeFlag(sortDirectionType);
        ColumnForSortingType sortingColumnType = ColumnForSortingType.valueOf(sortColumn.toUpperCase());
        List<JobVacancy> vacancies = fillContentExecutor.fillVacancy(userRole, searchField, startVacancyNumber, vacanciesQuantity);
        executeVacancySort(sortingColumnType, vacancies, sortDirectionTypeFlag);
        return vacancies;
    }

    @Override
    public List<JobRequest> filterRequest(FilterDataDTO filterDataDTO, String userRole,
                                          int startRequestNumber, int requestsQuantity) throws CustomExecutorException {
        String sortDirectionType = filterDataDTO.getSortDirectionType();
        String sortColumn = filterDataDTO.getSortColumn();
        String searchField = filterDataDTO.getSearchField();
        int orgId = filterDataDTO.getOrgId();
        boolean sortDirectionTypeFlag = setSortDirectionTypeFlag(sortDirectionType);
        ColumnForSortingType sortingColumnType = ColumnForSortingType.valueOf(sortColumn.toUpperCase());
        List<JobRequest> requests = fillContentExecutor.fillRequest(userRole, orgId, searchField , startRequestNumber, requestsQuantity);
        executeRequestSort(sortingColumnType, requests, sortDirectionTypeFlag);
        return requests;
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

    private void executeVacancySort(ColumnForSortingType sortingColumnType, List<JobVacancy> vacancies, boolean sortDirectionTypeFlag) throws CustomExecutorException {
        switch (sortingColumnType) {
            case SORT_BY_VAC_NAME:
                sorter(COMPARE_BY_NAME, vacancies, sortDirectionTypeFlag);
                break;
            case SORT_BY_EMPTY_COLUMN:
                break;
            case SORT_BY_ORGANIZATION:
                sorter(COMPARE_BY_ORG_NAME, vacancies, sortDirectionTypeFlag);
                break;
            default:
                throw new CustomExecutorException("Unknown sorting column!");
        }
    }

    private void executeRequestSort(ColumnForSortingType sortingColumnType, List<JobRequest> requests, boolean sortDirectionTypeFlag) throws CustomExecutorException {
        switch (sortingColumnType) {
            case SORT_BY_VAC_NAME:
                sorter(COMPARE_BY_VAC_NAME, requests, sortDirectionTypeFlag);
                break;
            case SORT_BY_EMPTY_COLUMN:
                break;
            case SORT_BY_USER_NAME:
                sorter(COMPARE_BY_USER_NAME, requests, sortDirectionTypeFlag);
                break;
            default:
                throw new CustomExecutorException("Unknown sorting column!");
        }
    }


    private <T> void sorter(Comparator<T> comparator, List<T> vacancies, boolean sortTypeFlag) {
        vacancies.sort(comparator);
        if (sortTypeFlag) {
            Collections.reverse(vacancies);
        }
    }
}
