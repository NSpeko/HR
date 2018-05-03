package by.molchanov.humanresources.executor;

import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.dao.UserDAO;
import by.molchanov.humanresources.entity.User;
import by.molchanov.humanresources.exception.CustomDAOException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.security.AESEncryption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.molchanov.humanresources.constant.AttributeNames.*;

public class AuthenticationExecutor {
    private static final Logger LOGGER = LogManager.getLogger();

    public void checkUserAccessory(RequestHolder requestHolder) throws CustomExecutorException {
        AESEncryption encryption = new AESEncryption();
        String email = requestHolder.getSingleRequestParameter(0, EMAIL);
        String password = requestHolder.getSingleRequestParameter(0, PASS);
        password = encryption.encryptionOfString(password);
        UserDAO userDAO = new UserDAO();
        try {
            List<User> users = userDAO.getUserByEmailAndPassword(email, password);
            if (users.size() == 1) {
                User user = users.get(0);
                requestHolder.addSessionAttribute(ROLE, user.getRole().getValue());
                requestHolder.addSessionAttribute(EMAIL, email);
                requestHolder.addSessionAttribute(FIRST_NAME, user.getFirstName());
                requestHolder.addSessionAttribute(LAST_NAME, user.getLastName());
                requestHolder.addSessionAttribute(USER_ID, user.getId());
            }
        } catch (CustomDAOException e) {
            requestHolder.addRequestAttribute(INFO_MESSAGE, "7");
            throw new CustomExecutorException(e);
        }
    }
}