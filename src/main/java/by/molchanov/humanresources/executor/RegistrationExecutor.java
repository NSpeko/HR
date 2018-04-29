package by.molchanov.humanresources.executor;

import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.dao.UserDAO;
import by.molchanov.humanresources.entity.User;
import by.molchanov.humanresources.entity.UserType;
import by.molchanov.humanresources.exception.CustomDAOException;
import by.molchanov.humanresources.security.AESEncryption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.molchanov.humanresources.constant.AttributeNames.PASS;
import static by.molchanov.humanresources.constant.AttributeNames.REPEAT_PASS;
import static by.molchanov.humanresources.constant.AttributeNames.EMAIL;
import static by.molchanov.humanresources.constant.AttributeNames.ROLE;
import static by.molchanov.humanresources.constant.AttributeNames.FIRST_NAME;
import static by.molchanov.humanresources.constant.AttributeNames.LAST_NAME;
import static by.molchanov.humanresources.constant.AttributeNames.INFO_MESSAGE;
import static by.molchanov.humanresources.validator.UserDataValidation.*;

public class RegistrationExecutor {
    private static final Logger LOGGER = LogManager.getLogger();

    public void userSignUp(RequestHolder requestHolder) {
        AESEncryption encryption = new AESEncryption();
        String email = requestHolder.getSingleRequestParameter(0, EMAIL);
        String password = requestHolder.getSingleRequestParameter(0, PASS);
        password = encryption.encryptionOfString(password);
        String repeatPass = requestHolder.getSingleRequestParameter(0, REPEAT_PASS);
        repeatPass = encryption.encryptionOfString(repeatPass);
        String firstName = requestHolder.getSingleRequestParameter(0, FIRST_NAME);
        String lastName = requestHolder.getSingleRequestParameter(0, LAST_NAME);
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
                UserType userType = UserType.ASPIRANT;
                User user = new User(email, password, firstName, lastName, userType);
                userDAO = new UserDAO();
                userDAO.persist(user);
                requestHolder.addRequestAttribute(INFO_MESSAGE, "content.information.successful.registration");
                requestHolder.addSessionAttribute(ROLE, userType.getValue());
                requestHolder.addSessionAttribute(EMAIL, email);
            }
        } catch (CustomDAOException e) {
            LOGGER.warn(e.getMessage(), e);
            requestHolder.addRequestAttribute(INFO_MESSAGE, "7");
        }
    }
}
