package by.molchanov.humanresources.command;

import by.molchanov.humanresources.controller.RequestHolder;

public interface ConcreteCommand {
    void execute(RequestHolder requestHolder);
}
