package by.molchanov.humanresources.command.impl;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.dto.VacancyDataDTO;
import by.molchanov.humanresources.entity.JobVacancy;
import by.molchanov.humanresources.entity.Organization;
import by.molchanov.humanresources.exception.CustomBrokerException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.impl.FillVacancyExecutorImpl;
import by.molchanov.humanresources.executor.impl.RegistrationExecutorImpl;

import java.util.List;

import static by.molchanov.humanresources.constant.SessionRequestAttributeNames.*;

public class JobVacancyRegistrationImpl implements ConcreteCommand {
    private static final RegistrationExecutorImpl REGISTRATION_EXECUTOR = RegistrationExecutorImpl.getInstance();
    private static final FillVacancyExecutorImpl FILL_VACANCY_EXECUTOR = FillVacancyExecutorImpl.getInstance();
    private static final int FIRST_INDEX = 0;

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        String vacancyName = requestHolder.getSingleRequestParameter(FIRST_INDEX, VACANCY_NAME);
        String vacancyRequirement = requestHolder.getSingleRequestParameter(FIRST_INDEX, VACANCY_REQUIREMENT);
        Organization organization = (Organization) requestHolder.getSessionAttribute(USER_ORG_INFO);
        VacancyDataDTO vacancyDataDTO = new VacancyDataDTO();
        vacancyDataDTO.setName(vacancyName);
        vacancyDataDTO.setRequirement(vacancyRequirement);
        vacancyDataDTO.setOrganization(organization);
        List<JobVacancy> vacancies;
        try {
            REGISTRATION_EXECUTOR.vacancySignUp(vacancyDataDTO);
            vacancies = FILL_VACANCY_EXECUTOR.fillVacancy();
        } catch (CustomExecutorException e) {
            throw new CustomBrokerException(e);
        }
        requestHolder.addRequestAttribute(INFO_MESSAGE, vacancyDataDTO.getInfoMessage());
        requestHolder.addRequestAttribute(VACANCY_LIST, vacancies);
    }
}
