package by.molchanov.humanresources.executor;

import by.molchanov.humanresources.controller.RequestHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.molchanov.humanresources.constant.SessionRequestAttributeNames.*;

public class LogOutExecutor {
    private static final LogOutExecutor LOG_OUT_EXECUTOR = new LogOutExecutor();

    private LogOutExecutor() {

    }

    public static LogOutExecutor getInstance() {
        return LOG_OUT_EXECUTOR;
    }

    public void logOut(RequestHolder requestHolder) {
        requestHolder.removeSessionAttribute(USER_INFO, USER_ORG_INFO);
    }
}
