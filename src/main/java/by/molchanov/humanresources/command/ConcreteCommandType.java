package by.molchanov.humanresources.command;

import by.molchanov.humanresources.command.broker.AuthenticationCommand;
import by.molchanov.humanresources.command.broker.RegistrationCommand;
import by.molchanov.humanresources.command.broker.ToRegistrationCommand;

import static by.molchanov.humanresources.command.ResponseType.FORWARD;

public enum ConcreteCommandType {
    AUTHENTICATION {
        @Override
        public ConcreteCommand getConcreteCommandBroker() {
            return new AuthenticationCommand();
        }

        @Override
        public ResponseType getResponseType() {
            return FORWARD;
        }
    },

    REGISTRATION {
        @Override
        public ConcreteCommand getConcreteCommandBroker() {
            return new RegistrationCommand();
        }

        @Override
        public ResponseType getResponseType() {
            return FORWARD;
        }
    },

    TOREGISTRATION {
        @Override
        public ConcreteCommand getConcreteCommandBroker() {
            return new ToRegistrationCommand();
        }

        @Override
        public ResponseType getResponseType() {
            return FORWARD;
        }
    };

    public abstract ConcreteCommand getConcreteCommandBroker();
    public abstract ResponseType getResponseType();
}
