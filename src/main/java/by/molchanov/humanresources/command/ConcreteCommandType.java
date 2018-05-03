package by.molchanov.humanresources.command;

import by.molchanov.humanresources.command.broker.AuthenticationCommand;
import by.molchanov.humanresources.command.broker.LogOutCommand;
import by.molchanov.humanresources.command.broker.OrgRegistrationCommand;
import by.molchanov.humanresources.command.broker.UserRegistrationCommand;

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

    USER_REGISTRATION {
        @Override
        public ConcreteCommand getConcreteCommandBroker() {
            return new UserRegistrationCommand();
        }

        @Override
        public ResponseType getResponseType() {
            return FORWARD;
        }
    },

    LOG_OUT {
        @Override
        public ConcreteCommand getConcreteCommandBroker() {
            return new LogOutCommand();
        }

        @Override
        public ResponseType getResponseType() {
            return FORWARD;
        }
    },

    ORG_REGISTRATION {
        @Override
        public ConcreteCommand getConcreteCommandBroker() {
            return new OrgRegistrationCommand();
        }

        @Override
        public ResponseType getResponseType() {
            return FORWARD;
        }
    },

    VACANCY_FILTER {
        @Override
        public ConcreteCommand getConcreteCommandBroker() {
            return null;
        }

        @Override
        public ResponseType getResponseType() {
            return null;
        }
    };

    public abstract ConcreteCommand getConcreteCommandBroker();
    public abstract ResponseType getResponseType();
}
