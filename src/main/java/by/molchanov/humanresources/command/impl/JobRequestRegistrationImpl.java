package by.molchanov.humanresources.command.impl;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.exception.CustomBrokerException;

public class JobRequestRegistrationImpl implements ConcreteCommand {
    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {

    }
}
