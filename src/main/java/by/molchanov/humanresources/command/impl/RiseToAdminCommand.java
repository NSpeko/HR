package by.molchanov.humanresources.command.impl;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.exception.CustomBrokerException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static by.molchanov.humanresources.command.SessionRequestAttributeName.USER_ID;

public class RiseToAdminCommand implements ConcreteCommand {
    private static final RiseToAdminCommand RISE_TO_ADMIN_COMMAND = new RiseToAdminCommand();
    private static final ConcreteCommand FILL_CONTENT_COMMAND = FillContentCommand.getInstance();

    private RiseToAdminCommand() {

    }

    public static RiseToAdminCommand getInstance() {
        return RISE_TO_ADMIN_COMMAND;
    }

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        List<String> usersId = new ArrayList<>(Arrays.asList(requestHolder.getRequestParameter(USER_ID)));
        if (!usersId.isEmpty()) {

        }
        FILL_CONTENT_COMMAND.execute(requestHolder);
    }
}