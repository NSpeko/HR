package by.molchanov.humanresources.command.impl;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.dto.FilterDataDTO;
import by.molchanov.humanresources.entity.JobRequest;
import by.molchanov.humanresources.entity.JobVacancy;
import by.molchanov.humanresources.entity.Organization;
import by.molchanov.humanresources.entity.User;
import by.molchanov.humanresources.exception.CustomBrokerException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.FillContentExecutor;
import by.molchanov.humanresources.executor.FilterExecutor;
import by.molchanov.humanresources.executor.impl.FillContentExecutorImpl;
import by.molchanov.humanresources.executor.impl.FilterExecutorImpl;

import java.util.List;

import static by.molchanov.humanresources.command.SessionRequestAttributeName.*;

/**
 * Class {@link FillContentCommand} is used for create page content, such as request, vacancies and other.
 *
 * @author MolcanovVladislav
 * @see ConcreteCommand
 */
public class FillContentCommand implements ConcreteCommand {
    private static final FillContentCommand FILL_VACANCY_COMMAND = new FillContentCommand();
    private static final FillContentExecutor FILL_VACANCY_EXECUTOR = FillContentExecutorImpl.getInstance();
    private static final FilterExecutor FILTER_EXECUTOR = FilterExecutorImpl.getInstance();

    private FillContentCommand() {

    }

    public static FillContentCommand getInstance() {
        return FILL_VACANCY_COMMAND;
    }

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        List<JobVacancy> vacancies;
        List<JobRequest> requests;
        String emptySearchField = "";
        int vacanciesCount = 0;
        int orgId = 0;
        FilterDataDTO filterDataDTO;
        String userRole = (String) requestHolder.getSessionAttribute(ROLE);
        User user = (User) requestHolder.getSessionAttribute(USER_INFO);
        Boolean vacFilterFlag = (Boolean) requestHolder.getSessionAttribute(VAC_FILTER_FLAG);
        Boolean reqFilterFlag = (Boolean) requestHolder.getSessionAttribute(REQUEST_FILTER_FLAG);
        int startVacancyNumber;
        try {
            startVacancyNumber = Integer.parseInt(requestHolder.getSingleRequestParameter(0, START_VACANCY_NUMBER));
        } catch (Exception e) {
            startVacancyNumber = 0;
        }

        int vacanciesQuantity;
        try {
            vacanciesQuantity = Integer.parseInt(requestHolder.getSingleRequestParameter(0, VACANCIES_QUANTITY));
        } catch (Exception e) {
            vacanciesQuantity = 10;
        }

        if (user != null) {
            Organization organization = user.getOrganization();
            orgId = organization.getId();
        }
        try {
            if (vacFilterFlag != null && vacFilterFlag) {
                filterDataDTO = (FilterDataDTO) requestHolder.getSessionAttribute(VAC_FILTER_DATA);
                vacancies = FILTER_EXECUTOR.filterVacancy(filterDataDTO, userRole, startVacancyNumber, vacanciesQuantity);
                vacanciesCount = FILL_VACANCY_EXECUTOR.getVacanciesCount(userRole, filterDataDTO.getSearchField());
            } else {
                vacancies = FILL_VACANCY_EXECUTOR.fillVacancy(userRole, emptySearchField, startVacancyNumber, vacanciesQuantity);
                vacanciesCount = FILL_VACANCY_EXECUTOR.getVacanciesCount(userRole, emptySearchField);
            }
            if (reqFilterFlag != null && reqFilterFlag) {
                filterDataDTO = (FilterDataDTO) requestHolder.getSessionAttribute(REQUEST_FILTER_DATA);
                requests = FILTER_EXECUTOR.filterRequest(filterDataDTO);
            } else {
                requests = FILL_VACANCY_EXECUTOR.fillRequest(userRole, orgId);
            }
        } catch (CustomExecutorException e) {
            throw new CustomBrokerException(e);
        }
        requestHolder.addRequestAttribute(VACANCY_LIST, vacancies);
        requestHolder.addRequestAttribute(REQUEST_LIST, requests);
        requestHolder.addRequestAttribute(START_VACANCY_NUMBER, startVacancyNumber);
        requestHolder.addRequestAttribute(VACANCIES_QUANTITY, vacanciesQuantity);
        requestHolder.addRequestAttribute(VACANCIES_COUNT, vacanciesCount);
    }
}
