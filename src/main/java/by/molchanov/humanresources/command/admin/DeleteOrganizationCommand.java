package by.molchanov.humanresources.command.admin;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.command.common.FillContentCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.exception.CustomBrokerException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.DeleteCloseExecutor;
import by.molchanov.humanresources.executor.impl.DeleteCloseExecutorImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static by.molchanov.humanresources.command.SessionRequestAttributeName.ORGANIZATION_ID;

/**
 * Class {@link DeleteOrganizationCommand} is used for delete organization from system.
 *
 * @author Molchanov Vladislav
 * @see ConcreteCommand
 */
public class DeleteOrganizationCommand implements ConcreteCommand {
    private static final DeleteOrganizationCommand DELETE_ORGANIZATION_COMMAND = new DeleteOrganizationCommand();
    private ConcreteCommand fillContentCommand = FillContentCommand.getInstance();
    private DeleteCloseExecutor deleteCloseExecutor = DeleteCloseExecutorImpl.getInstance();

    private DeleteOrganizationCommand() {

    }

    public static DeleteOrganizationCommand getInstance() {
        return DELETE_ORGANIZATION_COMMAND;
    }

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        List<String> organizationsId = new ArrayList<>(Arrays.asList(requestHolder.getRequestParameter(ORGANIZATION_ID)));
        if (!organizationsId.isEmpty()) {
            try {
                deleteCloseExecutor.deleteOrganization(organizationsId);
            } catch (CustomExecutorException e) {
                throw new CustomBrokerException(e);
            }
        }
        fillContentCommand.execute(requestHolder);
    }
}
