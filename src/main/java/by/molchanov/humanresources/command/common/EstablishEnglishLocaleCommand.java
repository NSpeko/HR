package by.molchanov.humanresources.command.common;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.exception.CustomBrokerException;

import static by.molchanov.humanresources.command.SessionRequestAttributeName.LOCALE;

/**
 * Class {@link EstablishEnglishLocaleCommand} is used for set english language to representation.
 *
 * @author Molchanov Vladislav
 * @see ConcreteCommand
 */
public class EstablishEnglishLocaleCommand implements ConcreteCommand {
    private static final EstablishEnglishLocaleCommand EST_ENG_LOC_COMMAND = new EstablishEnglishLocaleCommand();
    private ConcreteCommand fillContentCommand = FillContentCommand.getInstance();

    private static final String ENGLISH_LOCALE = "en_US";

    private EstablishEnglishLocaleCommand() {

    }

    public static EstablishEnglishLocaleCommand getInstance() {
        return EST_ENG_LOC_COMMAND;
    }

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        fillContentCommand.execute(requestHolder);
        requestHolder.addSessionAttribute(LOCALE, ENGLISH_LOCALE);
    }
}
