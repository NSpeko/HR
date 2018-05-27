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
import by.molchanov.humanresources.executor.FilterExecutor;
import by.molchanov.humanresources.executor.impl.FilterExecutorImpl;

import java.util.List;

import static by.molchanov.humanresources.command.SessionRequestAttributeName.*;
import static by.molchanov.humanresources.command.SessionRequestAttributeName.VACANCY_LIST;

/**
 * Class {@link RequestFilterCommand} is used for searching and filter requests content.
 *
 * @author MolcanovVladislav
 * @see ConcreteCommand
 */
public class RequestFilterCommand implements ConcreteCommand {
    private static final RequestFilterCommand REQUEST_FILTER_COMMAND = new RequestFilterCommand();
    private static final ConcreteCommand FILL_CONTENT_COMMAND = FillContentCommand.getInstance();

    private static final int FIRST_INDEX = 0;

    private RequestFilterCommand() {

    }

    public static RequestFilterCommand getInstance() {
        return REQUEST_FILTER_COMMAND;
    }

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        Boolean reqFilterFlag = (Boolean) requestHolder.getSessionAttribute(REQUEST_FILTER_FLAG);
        if (reqFilterFlag != null && reqFilterFlag) {
            requestHolder.removeSessionAttribute(REQUEST_FILTER_FLAG, REQUEST_FILTER_DATA);
        } else {
            String sortColumn = requestHolder.getSingleRequestParameter(FIRST_INDEX, SORT_COL);
            String sortDirectionType = requestHolder.getSingleRequestParameter(FIRST_INDEX, SORT_TYPE);
            String searchField = requestHolder.getSingleRequestParameter(FIRST_INDEX, SEARCH_FIELD);
            String userRole = (String) requestHolder.getSessionAttribute(ROLE);
            int orgId = 0;
            User user = (User) requestHolder.getSessionAttribute(USER_INFO);
            if (user != null) {
                Organization organization = user.getOrganization();
                orgId = organization.getId();
            }
            FilterDataDTO filterDataDTO = new FilterDataDTO();
            filterDataDTO.setSortColumn(sortColumn);
            filterDataDTO.setUserRole(userRole);
            filterDataDTO.setSortDirectionType(sortDirectionType);
            filterDataDTO.setOrgId(orgId);
            filterDataDTO.setSearchField(searchField);
            requestHolder.addSessionAttribute(REQUEST_FILTER_FLAG, true);
            requestHolder.addSessionAttribute(REQUEST_FILTER_DATA, filterDataDTO);
        }
        FILL_CONTENT_COMMAND.execute(requestHolder);
    }
}
