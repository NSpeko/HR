package by.molchanov.humanresources.dto;

import by.molchanov.humanresources.entity.JobVacancy;

import java.io.Serializable;

/**
 * Class {@link VacancyDataDTO} is used for transfer data about user between command and service level.
 *
 * @author Molchanov Vladislav
 */
public class VacancyDataDTO implements Serializable {
    private JobVacancy jobVacancy;
    private String infoMessage;
    private String vacancyId;

    public JobVacancy getJobVacancy() {
        return jobVacancy;
    }

    public void setJobVacancy(JobVacancy jobVacancy) {
        this.jobVacancy = jobVacancy;
    }

    public String getInfoMessage() {
        return infoMessage;
    }

    public void setInfoMessage(String infoMessage) {
        this.infoMessage = infoMessage;
    }

    public String getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(String vacancyId) {
        this.vacancyId = vacancyId;
    }
}
