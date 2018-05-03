package by.molchanov.humanresources.executor;

import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.dao.OrganizationDAO;
import by.molchanov.humanresources.dao.UserDAO;
import by.molchanov.humanresources.entity.Organization;
import by.molchanov.humanresources.entity.OrganizationType;
import by.molchanov.humanresources.entity.User;
import by.molchanov.humanresources.entity.UserStatusType;
import by.molchanov.humanresources.exception.CustomDAOException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.security.AESEncryption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.molchanov.humanresources.constant.AttributeNames.*;
import static by.molchanov.humanresources.validator.UserDataValidation.*;
import static by.molchanov.humanresources.validator.OrganizationDataValidation.*;

public class RegistrationExecutor {
    private static final Logger LOGGER = LogManager.getLogger();

    public void userSignUp(RequestHolder requestHolder) throws CustomExecutorException {
        AESEncryption encryption = new AESEncryption();
        String email = requestHolder.getSingleRequestParameter(0, EMAIL).trim();
        String password = requestHolder.getSingleRequestParameter(0, PASS);
        password = encryption.encryptionOfString(password).trim();
        String repeatPass = requestHolder.getSingleRequestParameter(0, REPEAT_PASS);
        repeatPass = encryption.encryptionOfString(repeatPass).trim();
        String firstName = requestHolder.getSingleRequestParameter(0, FIRST_NAME).trim();
        String lastName = requestHolder.getSingleRequestParameter(0, LAST_NAME).trim();
        UserDAO userDAO = new UserDAO();
        try {
            List<User> users = userDAO.findAll();
            boolean freeAddress = true;
            for (User user : users) {
                String userEmail = user.getEmail();
                if (email.equals(userEmail)) {
                    freeAddress = false;
                    break;
                }
            }
            if (!isEmailAddressCorrect(email)) {
                requestHolder.addRequestAttribute(INFO_MESSAGE, email);
            } else if (!isUserNameCorrect(firstName) && isUserNameCorrect(lastName)) {
                requestHolder.addRequestAttribute(INFO_MESSAGE, "1");
            } else if (!isUserPasswordCorrect(password)) {
                requestHolder.addRequestAttribute(INFO_MESSAGE, "3");
            } else if (!freeAddress) {
                requestHolder.addRequestAttribute(INFO_MESSAGE, "4");
            } else if (!password.equals(repeatPass)) {
                requestHolder.addRequestAttribute(INFO_MESSAGE, "5");
            } else {
                UserStatusType userStatusType = UserStatusType.ASPIRANT;
                User user = new User(email, password, firstName, lastName, userStatusType);
                user = userDAO.persist(user);
                requestHolder.addRequestAttribute(INFO_MESSAGE, "content.information.successful.registration");
                requestHolder.addSessionAttribute(ROLE, userStatusType.getValue());
                requestHolder.addSessionAttribute(EMAIL, email);
                requestHolder.addSessionAttribute(FIRST_NAME, firstName);
                requestHolder.addSessionAttribute(LAST_NAME, lastName);
                requestHolder.addSessionAttribute(USER_ID, user.getId());
            }
        } catch (CustomDAOException e) {
            LOGGER.warn(e.getMessage(), e);
            requestHolder.addRequestAttribute(INFO_MESSAGE, "7");
            throw new CustomExecutorException(e);
        }
    }

    public void orgSignUp(RequestHolder requestHolder) throws CustomExecutorException {
        String name = requestHolder.getSingleRequestParameter(0, ORG_NAME);
        String website = requestHolder.getSingleRequestParameter(0, WEBSITE);
        String description = requestHolder.getSingleRequestParameter(0, DESCRIPTION);
        String type = requestHolder.getSingleRequestParameter(0, TYPE).toUpperCase();
        if (!isOrgNameCorrect(name)) {
            requestHolder.addRequestAttribute(INFO_MESSAGE, "1");
        } else if (!isWEBSiteCorrect(website)) {
            requestHolder.addRequestAttribute(INFO_MESSAGE, "2");
        } else if (!isDescriptionCorrect(description)) {
            requestHolder.addRequestAttribute(INFO_MESSAGE, "3");
        } else {
            OrganizationType organizationType = OrganizationType.valueOf(type);
            OrganizationDAO organizationDAO = new OrganizationDAO();
            UserDAO userDAO = new UserDAO();
            Organization organization = new Organization(name, website, description, organizationType);
            try {
                organization = organizationDAO.persist(organization);
                String userId = (String) requestHolder.getSessionAttribute(USER_ID);
                User user = new User();
                user.setId(Integer.parseInt(userId));
                user.setOrganizationId(organization.getId());
                user.setRole(UserStatusType.DIRECTOR);
                userDAO.updateUserOrgIdRole(user);
                requestHolder.removeSessionAttribute(ROLE);
                requestHolder.addSessionAttribute(ROLE, UserStatusType.DIRECTOR.getValue());
                requestHolder.addSessionAttribute(ORG_NAME, name);
                requestHolder.addSessionAttribute(WEBSITE, website);
                requestHolder.addSessionAttribute(ORG_ID, organization.getId());
                requestHolder.addRequestAttribute(INFO_MESSAGE, "content.information.successful.registration");
            } catch (CustomDAOException e) {
                requestHolder.addRequestAttribute(INFO_MESSAGE, "7");
                throw new CustomExecutorException(e);
            }
        }
    }
}
