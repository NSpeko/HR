package by.molchanov.humanresources.command.broker;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.executor.AuthenticationExecutor;

public class AuthenticationCommand implements ConcreteCommand {
    @Override
    public String execute(RequestHolder requestHolder) {
        AuthenticationExecutor authenticationExecutor = new AuthenticationExecutor();
        return authenticationExecutor.checkUserAccessory(requestHolder);
    }
}
