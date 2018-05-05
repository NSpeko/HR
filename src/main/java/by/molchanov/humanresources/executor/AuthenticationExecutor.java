package by.molchanov.humanresources.executor;

import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.dao.OrganizationDAO;
import by.molchanov.humanresources.dao.UserDAO;
import by.molchanov.humanresources.dao.impl.OrganizationDAOImpl;
import by.molchanov.humanresources.dao.impl.UserDAOImpl;
import by.molchanov.humanresources.entity.Organization;
import by.molchanov.humanresources.entity.User;
import by.molchanov.humanresources.exception.CustomDAOException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.security.AESEncryption;

import java.util.List;

import static by.molchanov.humanresources.constant.PropertyMessageVariablesNames.REGISTRATION_ERROR;
import static by.molchanov.humanresources.constant.PropertyMessageVariablesNames.UNKNOWN_USER;
import static by.molchanov.humanresources.constant.SessionRequestAttributeNames.*;

public class AuthenticationExecutor {
    private static final AuthenticationExecutor AUTHENTICATION_EXECUTOR = new AuthenticationExecutor();

    private static final UserDAO USER_DAO = UserDAOImpl.getInstance();
    private static final OrganizationDAO ORGANIZATION_DAO = OrganizationDAOImpl.getInstance();

    private static final int PERMISSIBLE_VALUE = 1;
    private static final int FIRST_POSITION = 0;
    private static final int EMPTY_ORG_ID = 0;

    private AuthenticationExecutor() {

    }

    public static AuthenticationExecutor getInstance() {
        return AUTHENTICATION_EXECUTOR;
    }

    public void checkUserAccessory(RequestHolder requestHolder) throws CustomExecutorException {
        AESEncryption encryption = new AESEncryption();
        String email = requestHolder.getSingleRequestParameter(FIRST_POSITION, EMAIL);
        String password = requestHolder.getSingleRequestParameter(FIRST_POSITION, PASS);
        password = encryption.encryptionOfString(password);
        try {
            List<User> users = USER_DAO.getUsersByEmailAndPassword(email, password);
            if (users.size() == PERMISSIBLE_VALUE) {
                User user = users.get(FIRST_POSITION);
                requestHolder.addSessionAttribute(ROLE, user.getRole().getValue());
                requestHolder.addSessionAttribute(USER_INFO, user);
                if (user.getOrganizationId() != EMPTY_ORG_ID) {
                    Organization organization = ORGANIZATION_DAO.findById(user.getOrganizationId());
                    requestHolder.addSessionAttribute(USER_ORG_INFO, organization);
                }
            } else {
                requestHolder.addRequestAttribute(INFO_MESSAGE, UNKNOWN_USER);
            }
        } catch (CustomDAOException e) {
            requestHolder.addRequestAttribute(INFO_MESSAGE, REGISTRATION_ERROR);
            throw new CustomExecutorException(e);
        }
    }
}