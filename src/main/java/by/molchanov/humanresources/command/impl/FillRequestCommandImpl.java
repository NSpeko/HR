package by.molchanov.humanresources.command.impl;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.entity.JobRequest;
import by.molchanov.humanresources.entity.JobVacancy;
import by.molchanov.humanresources.entity.Organization;
import by.molchanov.humanresources.exception.CustomBrokerException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.FillContentExecutor;
import by.molchanov.humanresources.executor.impl.FillContentExecutorImpl;

import java.util.List;

import static by.molchanov.humanresources.constant.SessionRequestAttributeNames.*;

public class FillRequestCommandImpl implements ConcreteCommand {
    private static final FillRequestCommandImpl FILL_REQUEST_COMMAND = new FillRequestCommandImpl();
    private static final FillContentExecutor FILL_CONTENT_EXECUTOR = FillContentExecutorImpl.getInstance();

    private FillRequestCommandImpl() {

    }

    public static FillRequestCommandImpl getInstance() {
        return FILL_REQUEST_COMMAND;
    }

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        List<JobRequest> requests;
        int organizationId = 0;
        String userRole = (String) requestHolder.getSessionAttribute(ROLE);
        Organization organization = (Organization) requestHolder.getSessionAttribute(USER_ORG_INFO);
        if (organization != null) {
            organizationId = organization.getId();
        }
        try {
            requests = FILL_CONTENT_EXECUTOR.fillRequest(userRole, organizationId);
        } catch (CustomExecutorException e) {
            throw new CustomBrokerException(e);
        }
        requestHolder.addRequestAttribute(REQUEST_LIST, requests);
    }
}
