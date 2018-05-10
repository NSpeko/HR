package by.molchanov.humanresources.command.impl;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.dto.OrgDataDTO;
import by.molchanov.humanresources.entity.User;
import by.molchanov.humanresources.exception.CustomBrokerException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.RegistrationExecutor;
import by.molchanov.humanresources.executor.impl.RegistrationExecutorImpl;

import static by.molchanov.humanresources.constant.SessionRequestAttributeNames.*;
import static by.molchanov.humanresources.constant.SessionRequestAttributeNames.TYPE;

public class OrgRegistrationCommandImpl implements ConcreteCommand {
    private static final RegistrationExecutor REGISTRATION_EXECUTOR = RegistrationExecutorImpl.getInstance();
    private static final ConcreteCommand FILL_VACANCY_COMMAND = FillVacancyCommandImpl.getInstance();
    private static final int FIRST_INDEX = 0;

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        OrgDataDTO orgDataDTO = new OrgDataDTO();
        String name = requestHolder.getSingleRequestParameter(FIRST_INDEX, ORG_NAME);
        String website = requestHolder.getSingleRequestParameter(FIRST_INDEX, WEBSITE);
        String description = requestHolder.getSingleRequestParameter(FIRST_INDEX, DESCRIPTION);
        String type = requestHolder.getSingleRequestParameter(FIRST_INDEX, TYPE);
        User orgDirector = (User) requestHolder.getSessionAttribute(USER_INFO);
        orgDataDTO.setName(name);
        orgDataDTO.setWebsite(website);
        orgDataDTO.setDescription(description);
        orgDataDTO.setType(type);
        orgDataDTO.setOrgDirector(orgDirector);
        try {
            REGISTRATION_EXECUTOR.orgSignUp(orgDataDTO);
            FILL_VACANCY_COMMAND.execute(requestHolder);
        } catch (CustomExecutorException e) {
            throw new CustomBrokerException(e);
        }
        requestHolder.addSessionAttribute(ROLE, orgDataDTO.getDirectorRole());
        requestHolder.addSessionAttribute(USER_ORG_INFO, orgDataDTO.getOrganizationExemplar());
    }
}
