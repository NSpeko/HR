package by.molchanov.humanresources.executor;

import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.dao.UserDAO;
import by.molchanov.humanresources.entity.User;
import by.molchanov.humanresources.exception.CustomDAOException;
import by.molchanov.humanresources.security.AESEncryption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.molchanov.humanresources.constant.AttributeNames.EMAIL;
import static by.molchanov.humanresources.constant.AttributeNames.PASS;
import static by.molchanov.humanresources.constant.AttributeNames.ROLE;

import java.util.List;

public class AuthenticationExecutor {
    private static final Logger LOGGER = LogManager.getLogger();

    public void checkUserAccessory(RequestHolder requestHolder) {
        AESEncryption encryption = new AESEncryption();
        String email = requestHolder.getSingleRequestParameter(0, EMAIL);
        String password = requestHolder.getSingleRequestParameter(0, PASS);
        password = encryption.encryptionOfString(password);
        UserDAO userDAO = new UserDAO();
        try {
            List<User> users = userDAO.getUserRolesByEmailAndPassword(email, password);
            if (users.size() == 1) {
                String userRole = users.get(0).getRole().getValue();
                requestHolder.addSessionAttribute(ROLE, userRole);
                requestHolder.addSessionAttribute(EMAIL, email);
            }
        } catch (CustomDAOException e) {
            LOGGER.warn(e.getMessage(), e);
        }
    }
}