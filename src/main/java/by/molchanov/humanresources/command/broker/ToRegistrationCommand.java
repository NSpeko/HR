package by.molchanov.humanresources.command.broker;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.resource.PageLocationManager;

public class ToRegistrationCommand implements ConcreteCommand {
    @Override
    public String execute(RequestHolder requestHolder) {
        return PageLocationManager.getInstance().getProperty("page.location.registration");
    }
}
