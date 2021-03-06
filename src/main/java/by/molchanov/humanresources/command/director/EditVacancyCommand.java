package by.molchanov.humanresources.command.director;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.command.common.FillContentCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.dto.VacancyDataDTO;
import by.molchanov.humanresources.entity.JobVacancy;
import by.molchanov.humanresources.exception.CustomBrokerException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.EditExecutor;
import by.molchanov.humanresources.executor.impl.EditExecutorImpl;

import static by.molchanov.humanresources.command.SessionRequestAttributeName.*;

/**
 * Class {@link EditVacancyCommand} is used for making updates to vacancy.
 *
 * @author Molchanov Vladislav
 * @see ConcreteCommand
 */
public class EditVacancyCommand implements ConcreteCommand {
    private static final EditVacancyCommand EDIT_VACANCY_COMMAND = new EditVacancyCommand();
    private ConcreteCommand fillContentCommand = FillContentCommand.getInstance();
    private EditExecutor editExecutor = EditExecutorImpl.getInstance();

    private static final int FIRST_INDEX = 0;

    private EditVacancyCommand() {

    }

    public static EditVacancyCommand getInstance() {
        return EDIT_VACANCY_COMMAND;
    }

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        String vacancyId = requestHolder.getSingleRequestParameter(FIRST_INDEX, VACANCY_ID);
        String vacancyName = requestHolder.getSingleRequestParameter(FIRST_INDEX, VACANCY_NAME);
        String vacancyRequirement = requestHolder.getSingleRequestParameter(FIRST_INDEX, VACANCY_REQUIREMENT);
        JobVacancy jobVacancy = new JobVacancy();
        jobVacancy.setName(vacancyName);
        jobVacancy.setRequirement(vacancyRequirement);
        VacancyDataDTO vacancyDataDTO = new VacancyDataDTO();
        vacancyDataDTO.setJobVacancy(jobVacancy);
        vacancyDataDTO.setVacancyId(vacancyId);
        try {
            editExecutor.editVacancy(vacancyDataDTO);
            fillContentCommand.execute(requestHolder);
        } catch (CustomExecutorException e) {
            throw new CustomBrokerException(e);
        }
        requestHolder.addRequestAttribute(INFO_MESSAGE, vacancyDataDTO.getInfoMessage());
    }
}
