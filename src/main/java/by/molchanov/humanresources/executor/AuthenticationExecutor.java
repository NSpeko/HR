package by.molchanov.humanresources.executor;

import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.dao.UserDAO;
import by.molchanov.humanresources.entity.User;
import by.molchanov.humanresources.exception.CustomDAOException;
import by.molchanov.humanresources.resource.PageLocationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.molchanov.humanresources.constant.AttributeNames.EMAIL;
import static by.molchanov.humanresources.constant.AttributeNames.PASS;
import static by.molchanov.humanresources.constant.AttributeNames.INFO_MESSAGE;

import java.util.List;

public class AuthenticationExecutor {
    private static final Logger LOGGER = LogManager.getLogger();

    public String checkUserAccessory(RequestHolder requestHolder) {
        String page = PageLocationManager.getInstance().getProperty("page.location.home");;
        String email = requestHolder.getSingleRequestParameter(0, EMAIL);
        String password = requestHolder.getSingleRequestParameter(0, PASS);
        String userEmail;
        String userPassword;
        UserDAO userDAO = new UserDAO();
        try {
            List<User> users = userDAO.findAll();
            for (User user : users) {
                userEmail = user.getEmail();
                userPassword = user.getPass();
                if (email.equals(userEmail) && password.equals(userPassword)) {
                    requestHolder.addRequestAttribute(INFO_MESSAGE, "Successful authentication!");
                    page = PageLocationManager.getInstance().getProperty("page.location.main");
                    return page;
                }
            }
        } catch (CustomDAOException e) {
            LOGGER.warn(e.getMessage(), e);
        }
        return page;
    }
}
