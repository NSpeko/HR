package by.molchanov.humanresources.executor;

import by.molchanov.humanresources.exception.CustomExecutorException;

public interface DeleteExecutor {
    void deleteVacancy(String vacancyId) throws CustomExecutorException;
}
