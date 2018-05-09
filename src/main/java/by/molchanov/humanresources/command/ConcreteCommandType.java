package by.molchanov.humanresources.command;

import by.molchanov.humanresources.command.impl.*;

import static by.molchanov.humanresources.command.ResponseType.FORWARD;

public enum ConcreteCommandType {
    AUTHENTICATION {
        @Override
        public ConcreteCommand getConcreteCommandBroker() {
            return new AuthenticationCommandImpl();
        }

        @Override
        public ResponseType getResponseType() {
            return FORWARD;
        }
    },

    USER_REGISTRATION {
        @Override
        public ConcreteCommand getConcreteCommandBroker() {
            return new UserRegistrationCommandImpl();
        }

        @Override
        public ResponseType getResponseType() {
            return FORWARD;
        }
    },

    LOG_OUT {
        @Override
        public ConcreteCommand getConcreteCommandBroker() {
            return new LogOutCommandImpl();
        }

        @Override
        public ResponseType getResponseType() {
            return FORWARD;
        }
    },

    ORG_REGISTRATION {
        @Override
        public ConcreteCommand getConcreteCommandBroker() {
            return new OrgRegistrationCommandImpl();
        }

        @Override
        public ResponseType getResponseType() {
            return FORWARD;
        }
    },

    VACANCY_FILTER {
        @Override
        public ConcreteCommand getConcreteCommandBroker() {
            return new VacancyFilterCommandImpl();
        }

        @Override
        public ResponseType getResponseType() {
            return FORWARD;
        }
    },

    VACANCY_REGISTRATION {
        @Override
        public ConcreteCommand getConcreteCommandBroker() {
            return new VacancyRegistrationImpl();
        }

        @Override
        public ResponseType getResponseType() {
            return FORWARD;
        }
    },

    REQUEST_REGISTRATION {
        @Override
        public ConcreteCommand getConcreteCommandBroker() {
            return new JobRequestRegistrationImpl();
        }

        @Override
        public ResponseType getResponseType() {
            return FORWARD;
        }
    },
    CONFIRM_VACANCY {
        @Override
        public ConcreteCommand getConcreteCommandBroker() {
            return new ConfirmVacancyImpl();
        }

        @Override
        public ResponseType getResponseType() {
            return FORWARD;
        }
    };

    public abstract ConcreteCommand getConcreteCommandBroker();
    public abstract ResponseType getResponseType();
}
