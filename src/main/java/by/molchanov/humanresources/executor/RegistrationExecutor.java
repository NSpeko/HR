package by.molchanov.humanresources.executor;

import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.dao.JobVacancyDAO;
import by.molchanov.humanresources.dao.OrganizationDAO;
import by.molchanov.humanresources.dao.UserDAO;
import by.molchanov.humanresources.dao.impl.JobVacancyDAOImpl;
import by.molchanov.humanresources.dao.impl.OrganizationDAOImpl;
import by.molchanov.humanresources.dao.impl.UserDAOImpl;
import by.molchanov.humanresources.entity.*;
import by.molchanov.humanresources.exception.CustomDAOException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.security.AESEncryption;

import java.util.List;

import static by.molchanov.humanresources.constant.PropertyMessageVariablesNames.*;
import static by.molchanov.humanresources.constant.SessionRequestAttributeNames.*;
import static by.molchanov.humanresources.validator.UserDataValidation.*;
import static by.molchanov.humanresources.validator.OrganizationDataValidation.*;

public class RegistrationExecutor {
    private static final RegistrationExecutor REGISTRATION_EXECUTOR = new RegistrationExecutor();

    private static final UserDAO USER_DAO = UserDAOImpl.getInstance();
    private static final OrganizationDAO ORGANIZATION_DAO = OrganizationDAOImpl.getInstance();
    private static final JobVacancyDAO JOB_VACANCY_DAO = JobVacancyDAOImpl.getInstance();

    private static final int FIRST_INDEX = 0;

    private RegistrationExecutor() {

    }

    public static RegistrationExecutor getInstance() {
        return REGISTRATION_EXECUTOR;
    }

    public void userSignUp(RequestHolder requestHolder) throws CustomExecutorException {
        AESEncryption encryption = new AESEncryption();
        String email = requestHolder.getSingleRequestParameter(FIRST_INDEX, EMAIL).trim();
        String password = requestHolder.getSingleRequestParameter(FIRST_INDEX, PASS);
        password = encryption.encryptionOfString(password).trim();
        String repeatPass = requestHolder.getSingleRequestParameter(FIRST_INDEX, REPEAT_PASS);
        repeatPass = encryption.encryptionOfString(repeatPass).trim();
        String firstName = requestHolder.getSingleRequestParameter(FIRST_INDEX, FIRST_NAME).trim();
        String lastName = requestHolder.getSingleRequestParameter(FIRST_INDEX, LAST_NAME).trim();
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
                requestHolder.addRequestAttribute(INFO_MESSAGE, USER_REGISTRATION_INCORRECT_EMAIL);
            } else if (!isUserNameCorrect(firstName) && isUserNameCorrect(lastName)) {
                requestHolder.addRequestAttribute(INFO_MESSAGE, USER_REGISTRATION_INCORRECT_NAME_SURNAME);
            } else if (!isUserPasswordCorrect(password)) {
                requestHolder.addRequestAttribute(INFO_MESSAGE, USER_REGISTRATION_INCORRECT_PASS);
            } else if (!freeAddress) {
                requestHolder.addRequestAttribute(INFO_MESSAGE, USER_REGISTRATION_NOT_AVAILABLE_EMAIL_ADDRESS);
            } else if (!password.equals(repeatPass)) {
                requestHolder.addRequestAttribute(INFO_MESSAGE, USER_REGISTRATION_NOT_SAME_PASS);
            } else {
                UserStatusType userStatusType = UserStatusType.ASPIRANT;
                User user = new User(email, password, firstName, lastName, userStatusType);
                user = USER_DAO.persist(user);
                requestHolder.addRequestAttribute(INFO_MESSAGE, USER_REGISTRATION_SUCCESSFUL_REGISTRATION);
                requestHolder.addSessionAttribute(ROLE, userStatusType.getValue());
                requestHolder.addSessionAttribute(USER_INFO, user);
            }
        } catch (CustomDAOException e) {
            requestHolder.addRequestAttribute(INFO_MESSAGE, REGISTRATION_ERROR);
            throw new CustomExecutorException(e);
        }
    }

    public void orgSignUp(RequestHolder requestHolder) throws CustomExecutorException {
        String name = requestHolder.getSingleRequestParameter(FIRST_INDEX, ORG_NAME);
        String website = requestHolder.getSingleRequestParameter(FIRST_INDEX, WEBSITE);
        String description = requestHolder.getSingleRequestParameter(FIRST_INDEX, DESCRIPTION);
        String type = requestHolder.getSingleRequestParameter(FIRST_INDEX, TYPE).toUpperCase();
        if (!isOrgNameCorrect(name)) {
            requestHolder.addRequestAttribute(INFO_MESSAGE, ORG_REGISTRATION_INCORRECT_NAME);
        } else if (!isWEBSiteCorrect(website)) {
            requestHolder.addRequestAttribute(INFO_MESSAGE, ORG_REGISTRATION_INCORRECT_WEBSITE_URL);
        } else if (!isDescriptionCorrect(description)) {
            requestHolder.addRequestAttribute(INFO_MESSAGE, ORG_REGISTRATION_INCORRECT_DESCRIPTION);
        } else {
            OrganizationType organizationType = OrganizationType.valueOf(type);
            Organization organization = new Organization(name, website, description, organizationType);
            try {
                organization = ORGANIZATION_DAO.persist(organization);
                User user = (User) requestHolder.getSessionAttribute(USER_INFO);
                user.setOrganizationId(organization.getId());
                user.setRole(UserStatusType.DIRECTOR);
                USER_DAO.updateUserOrgIdRole(user);
                requestHolder.removeSessionAttribute(ROLE);
                requestHolder.addSessionAttribute(ROLE, UserStatusType.DIRECTOR.getValue());
                requestHolder.addSessionAttribute(USER_ORG_INFO, organization);
                requestHolder.addRequestAttribute(INFO_MESSAGE, USER_REGISTRATION_SUCCESSFUL_REGISTRATION);
            } catch (CustomDAOException e) {
                requestHolder.addRequestAttribute(INFO_MESSAGE, REGISTRATION_ERROR);
                throw new CustomExecutorException(e);
            }
        }
    }

    public void vacancySignUp(RequestHolder requestHolder) throws CustomExecutorException {
        String vacancyName = requestHolder.getSingleRequestParameter(FIRST_INDEX, VACANCY_NAME);
        String vacancyRequirement = requestHolder.getSingleRequestParameter(FIRST_INDEX, VACANCY_REQUIREMENT);
        Organization organization = (Organization) requestHolder.getSessionAttribute(USER_ORG_INFO);
        int organizationId = organization.getId();
        JobVacancyStatusType statusType = JobVacancyStatusType.NEW;
        JobVacancy jobVacancy = new JobVacancy(organizationId, vacancyName, vacancyRequirement, statusType);
        try {
            JOB_VACANCY_DAO.persist(jobVacancy);
        } catch (CustomDAOException e) {
            throw new CustomExecutorException();
        }
    }
}
