package by.molchanov.humanresources.executor.impl;

import by.molchanov.humanresources.dao.JobVacancyDAO;
import by.molchanov.humanresources.dao.OrganizationDAO;
import by.molchanov.humanresources.dao.UserDAO;
import by.molchanov.humanresources.dao.impl.JobVacancyDAOImpl;
import by.molchanov.humanresources.dao.impl.OrganizationDAOImpl;
import by.molchanov.humanresources.dao.impl.UserDAOImpl;
import by.molchanov.humanresources.dto.OrgDataDTO;
import by.molchanov.humanresources.dto.UserDataDTO;
import by.molchanov.humanresources.dto.VacancyDataDTO;
import by.molchanov.humanresources.entity.*;
import by.molchanov.humanresources.exception.CustomDAOException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.RegistrationExecutor;
import by.molchanov.humanresources.security.AESEncryption;

import java.util.List;

import static by.molchanov.humanresources.constant.PropertyMessageVariablesNames.*;
import static by.molchanov.humanresources.validator.UserDataValidation.*;
import static by.molchanov.humanresources.validator.OrganizationDataValidation.*;
import static by.molchanov.humanresources.validator.VacancyDataValidation.*;

public class RegistrationExecutorImpl implements RegistrationExecutor {
    private static final RegistrationExecutorImpl REGISTRATION_EXECUTOR = new RegistrationExecutorImpl();

    private static final UserDAO USER_DAO = UserDAOImpl.getInstance();
    private static final OrganizationDAO ORGANIZATION_DAO = OrganizationDAOImpl.getInstance();
    private static final JobVacancyDAO JOB_VACANCY_DAO = JobVacancyDAOImpl.getInstance();

    private RegistrationExecutorImpl() {

    }

    public static RegistrationExecutorImpl getInstance() {
        return REGISTRATION_EXECUTOR;
    }

    @Override
    public void userSignUp(UserDataDTO userDataDTO) throws CustomExecutorException {
        AESEncryption encryption = new AESEncryption();
        String email = userDataDTO.getEmail();
        String password = userDataDTO.getPassword();
        password = encryption.encryptionOfString(password).trim();
        String repeatPass = userDataDTO.getRepeatPassword();
        repeatPass = encryption.encryptionOfString(repeatPass).trim();
        String firstName = userDataDTO.getFirstName();
        String lastName = userDataDTO.getLastName();
        String infoMessage = USER_REGISTRATION_SUCCESSFUL_REGISTRATION;
        try {
            List<User> users = USER_DAO.findAll();
            boolean freeAddress = true;
            for (User user : users) {
                String userEmail = user.getEmail();
                if (email.equals(userEmail)) {
                    freeAddress = false;
                    break;
                }
            }
            if (!isEmailAddressCorrect(email)) {
                infoMessage = USER_REGISTRATION_INCORRECT_EMAIL;
            } else if (!isUserNameCorrect(firstName) && isUserNameCorrect(lastName)) {
                infoMessage = USER_REGISTRATION_INCORRECT_NAME_SURNAME;
            } else if (!isUserPasswordCorrect(password)) {
                infoMessage = USER_REGISTRATION_INCORRECT_PASS;
            } else if (!freeAddress) {
                infoMessage = USER_REGISTRATION_NOT_AVAILABLE_EMAIL_ADDRESS;
            } else if (!password.equals(repeatPass)) {
                infoMessage = USER_REGISTRATION_NOT_SAME_PASS;
            } else {
                UserStatusType userStatusType = UserStatusType.ASPIRANT;
                User user = new User(email, password, firstName, lastName, userStatusType);
                user = USER_DAO.persist(user);
                userDataDTO.setRole(userStatusType.getValue());
                userDataDTO.setUserExemplar(user);
            }
        } catch (CustomDAOException e) {
            throw new CustomExecutorException(e);
        }
        userDataDTO.setInfoMessage(infoMessage);
    }

    @Override
    public void orgSignUp(OrgDataDTO orgDataDTO) throws CustomExecutorException {
        String name = orgDataDTO.getName();
        String website = orgDataDTO.getWebsite();
        String description = orgDataDTO.getDescription();
        String type = orgDataDTO.getType().toUpperCase();
        User user = orgDataDTO.getOrgDirector();
        String infoMessage = USER_REGISTRATION_SUCCESSFUL_REGISTRATION;
        if (!isOrgNameCorrect(name)) {
            infoMessage = ORG_REGISTRATION_INCORRECT_NAME;
        } else if (!isWEBSiteCorrect(website)) {
            infoMessage = ORG_REGISTRATION_INCORRECT_WEBSITE_URL;
        } else if (!isDescriptionCorrect(description)) {
            infoMessage = ORG_REGISTRATION_INCORRECT_DESCRIPTION;
        } else {
            OrganizationType organizationType = OrganizationType.valueOf(type);
            Organization organization = new Organization(name, website, description, organizationType);
            try {
                organization = ORGANIZATION_DAO.persist(organization);
                user.setOrganizationId(organization.getId());
                UserStatusType userType = UserStatusType.DIRECTOR;
                user.setRole(userType);
                USER_DAO.updateUserOrgIdRole(user);
                orgDataDTO.setType(userType.getValue());
                orgDataDTO.setOrganizationExemplar(organization);
            } catch (CustomDAOException e) {
                throw new CustomExecutorException(e);
            }
        }
        orgDataDTO.setInfoMessage(infoMessage);
    }

    @Override
    public void vacancySignUp(VacancyDataDTO vacancyDataDTO) throws CustomExecutorException {
        String vacancyName = vacancyDataDTO.getName();
        String vacancyRequirement = vacancyDataDTO.getRequirement();
        Organization organization = vacancyDataDTO.getOrganization();
        String infoMessage = VACANCY_SUCCESSFUL_REGISTRATION;
        if (!isVacancyNameCorrect(vacancyName)) {
            infoMessage = VACANCY_INCORRECT_NAME;
        } else if (!isRequirementCorrect(vacancyRequirement)) {
            infoMessage = VACANCY_INCORRECT_REQUIREMENT;
        } else {
            int organizationId = organization.getId();
            JobVacancyStatusType statusType = JobVacancyStatusType.NEW;
            JobVacancy jobVacancy = new JobVacancy(organizationId, vacancyName, vacancyRequirement, statusType);
            try {
                JOB_VACANCY_DAO.persist(jobVacancy);
            } catch (CustomDAOException e) {
                throw new CustomExecutorException();
            }
        }
        vacancyDataDTO.setInfoMessage(infoMessage);
    }
}
