package by.molchanov.humanresources.command.impl;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.dto.UserDataDTO;
import by.molchanov.humanresources.entity.JobVacancy;
import by.molchanov.humanresources.exception.CustomBrokerException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.impl.AuthenticationExecutorImpl;
import by.molchanov.humanresources.executor.impl.FillVacancyExecutorImpl;

import java.util.List;

import static by.molchanov.humanresources.constant.SessionRequestAttributeNames.*;

public class AuthenticationCommandImpl implements ConcreteCommand {
    private static final AuthenticationExecutorImpl AUTHENTICATION_EXECUTOR = AuthenticationExecutorImpl.getInstance();
    private static final FillVacancyExecutorImpl FILL_VACANCY_EXECUTOR = FillVacancyExecutorImpl.getInstance();
    private static final int FIRST_POSITION = 0;

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        UserDataDTO userDataDTO;
        List<JobVacancy> vacancies;
        String email = requestHolder.getSingleRequestParameter(FIRST_POSITION, EMAIL);
        String password = requestHolder.getSingleRequestParameter(FIRST_POSITION, PASS);
        try {
            userDataDTO = AUTHENTICATION_EXECUTOR.checkUserAccessory(email, password);
            vacancies = FILL_VACANCY_EXECUTOR.fillVacancy();
        } catch (CustomExecutorException e) {
            throw new CustomBrokerException(e);
        }
        requestHolder.addSessionAttribute(ROLE, userDataDTO.getRole());
        requestHolder.addSessionAttribute(USER_INFO, userDataDTO.getUserExemplar());
        requestHolder.addSessionAttribute(USER_ORG_INFO, userDataDTO.getUserOrganizationInfo());
        requestHolder.addRequestAttribute(INFO_MESSAGE, userDataDTO.getInfoMessage());
        requestHolder.addRequestAttribute(VACANCY_LIST, vacancies);
    }
}
