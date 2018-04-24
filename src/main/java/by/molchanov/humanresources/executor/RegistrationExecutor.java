package by.molchanov.humanresources.executor;

import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.dao.UserDAO;
import by.molchanov.humanresources.entity.User;
import by.molchanov.humanresources.entity.UserType;
import by.molchanov.humanresources.exception.CustomDAOException;
import by.molchanov.humanresources.resource.PageLocationManager;
import by.molchanov.humanresources.validator.UserDataValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.molchanov.humanresources.constant.AttributeNames.PASS;
import static by.molchanov.humanresources.constant.AttributeNames.REPEAT_PASS;
import static by.molchanov.humanresources.constant.AttributeNames.EMAIL;
import static by.molchanov.humanresources.constant.AttributeNames.ERROR_MESSAGE;
import static by.molchanov.humanresources.constant.AttributeNames.FIRST_NAME;
import static by.molchanov.humanresources.constant.AttributeNames.LAST_NAME;
import static by.molchanov.humanresources.constant.AttributeNames.INFO_MESSAGE;

public class RegistrationExecutor {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String CURRENT_PAGE = PageLocationManager.getInstance().getProperty("page.location.registration");
    private static final String SUCCESSFUL_REGISTRATION_PAGE = PageLocationManager.getInstance().getProperty("page.location.main");

    public String userSignUp(RequestHolder requestHolder) {
        String page = CURRENT_PAGE;
        String email = requestHolder.getSingleRequestParameter(0, EMAIL);
        String pass = requestHolder.getSingleRequestParameter(0, PASS);
        String repeatPass = requestHolder.getSingleRequestParameter(0, REPEAT_PASS);
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
            if (!freeAddress) {
                requestHolder.addRequestAttribute(ERROR_MESSAGE, "Email: '" + email + "' address already in use!");
            }
            else if (!pass.equals(repeatPass)) {
                requestHolder.addRequestAttribute(ERROR_MESSAGE, "Passwords must be the same!");
            } else {
                page = SUCCESSFUL_REGISTRATION_PAGE;
                requestHolder.addRequestAttribute(INFO_MESSAGE, "Successful registration!");
                UserType userType = UserType.ASPIRANT;
                User user = new User(email, pass, firstName, lastName, userType);
                userDAO = new UserDAO();
                userDAO.persist(user);
            }
        } catch (CustomDAOException e) {
            LOGGER.warn(e.getMessage(), e);
        }
        return page;
    }
}
