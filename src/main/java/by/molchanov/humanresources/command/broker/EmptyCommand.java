package by.molchanov.humanresources.command.broker;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.executor.FillVacancyExecutor;

public class EmptyCommand implements ConcreteCommand {
    @Override
    public void execute(RequestHolder requestHolder) {
        FillVacancyExecutor fillVacancyExecutor = new FillVacancyExecutor();
        fillVacancyExecutor.fillVacancy(requestHolder);
    }
}
