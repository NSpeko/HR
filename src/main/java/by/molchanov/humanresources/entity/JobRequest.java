package by.molchanov.humanresources.entity;

import java.io.Serializable;

public class JobRequest implements Serializable {
    private int id;
    private int jobVacancyId;
    private int userId;
    private String resume;
    private JobRequestStatusType status;

    public JobRequest() {
    }

    public JobRequest(int jobVacancyId, int userId, String resume, JobRequestStatusType status) {
        this.jobVacancyId = jobVacancyId;
        this.userId = userId;
        this.resume = resume;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJobVacancyId() {
        return jobVacancyId;
    }

    public void setJobVacancyId(int jobVacancyId) {
        this.jobVacancyId = jobVacancyId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public JobRequestStatusType getStatus() {
        return status;
    }

    public void setStatus(JobRequestStatusType status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JobRequest that = (JobRequest) o;

        if (id != that.id) return false;
        if (jobVacancyId != that.jobVacancyId) return false;
        if (userId != that.userId) return false;
        if (resume != null ? !resume.equals(that.resume) : that.resume != null) return false;
        return status == that.status;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + jobVacancyId;
        result = 31 * result + userId;
        result = 31 * result + (resume != null ? resume.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "JobRequest{" +
                "id=" + id +
                ", jobVacancyId=" + jobVacancyId +
                ", userId=" + userId +
                ", resume='" + resume + '\'' +
                ", status=" + status +
                '}';
    }
}
