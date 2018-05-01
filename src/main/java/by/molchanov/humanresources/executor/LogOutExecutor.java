package by.molchanov.humanresources.executor;

import by.molchanov.humanresources.controller.RequestHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.molchanov.humanresources.constant.AttributeNames.*;

public class LogOutExecutor {
    private static final Logger LOGGER = LogManager.getLogger();

    public void logOut(RequestHolder requestHolder) {
        String currentUserRole = (String) requestHolder.getSessionAttribute(ROLE);
        String currentUserEmail = (String) requestHolder.getSessionAttribute(EMAIL);
        LOGGER.info(currentUserRole + currentUserEmail + " log out from system");
        requestHolder.removeSessionAttribute(EMAIL, ROLE, FIRST_NAME, LAST_NAME, USER_ID, ORG_ID, ORG_NAME, WEBSITE);
    }
}
