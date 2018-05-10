package by.molchanov.humanresources.command.impl;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.dto.UserDataDTO;
import by.molchanov.humanresources.exception.CustomBrokerException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.RegistrationExecutor;
import by.molchanov.humanresources.executor.impl.RegistrationExecutorImpl;

import static by.molchanov.humanresources.constant.SessionRequestAttributeNames.*;
import static by.molchanov.humanresources.constant.SessionRequestAttributeNames.FIRST_NAME;
import static by.molchanov.humanresources.constant.SessionRequestAttributeNames.LAST_NAME;

public class UserRegistrationCommandImpl implements ConcreteCommand {
    private static final RegistrationExecutor REGISTRATION_EXECUTOR = RegistrationExecutorImpl.getInstance();
    private static final ConcreteCommand FILL_VACANCY_COMMAND = FillVacancyCommandImpl.getInstance();
    private static final int FIRST_INDEX = 0;

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        String email = requestHolder.getSingleRequestParameter(FIRST_INDEX, EMAIL).trim();
        String password = requestHolder.getSingleRequestParameter(FIRST_INDEX, PASS).trim();
        String repeatPass = requestHolder.getSingleRequestParameter(FIRST_INDEX, REPEAT_PASS).trim();
        String firstName = requestHolder.getSingleRequestParameter(FIRST_INDEX, FIRST_NAME).trim();
        String lastName = requestHolder.getSingleRequestParameter(FIRST_INDEX, LAST_NAME).trim();
        UserDataDTO userDataDTO = new UserDataDTO();
        userDataDTO.setEmail(email);
        userDataDTO.setPassword(password);
        userDataDTO.setRepeatPassword(repeatPass);
        userDataDTO.setFirstName(firstName);
        userDataDTO.setLastName(lastName);
        try {
            REGISTRATION_EXECUTOR.userSignUp(userDataDTO);
            FILL_VACANCY_COMMAND.execute(requestHolder);
        } catch (CustomExecutorException e) {
            throw new CustomBrokerException(e);
        }
        requestHolder.addSessionAttribute(USER_INFO, userDataDTO.getUserExemplar());
        requestHolder.addSessionAttribute(ROLE, userDataDTO.getRole());
        requestHolder.addRequestAttribute(INFO_MESSAGE, userDataDTO.getInfoMessage());
    }
}
