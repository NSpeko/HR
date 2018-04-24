package by.molchanov.humanresources.entity;

public class JobVacancy {
    private int id;
    private int organizationId;
    private String name;
    private String uploadDate;
    private String requirement;
    private JobVacancyStatusType status;

    public JobVacancy() {
    }

    public JobVacancy(int organizationId, String name, String uploadDate, String requirement, JobVacancyStatusType status) {
        this.organizationId = organizationId;
        this.name = name;
        this.uploadDate = uploadDate;
        this.requirement = requirement;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public JobVacancyStatusType getStatus() {
        return status;
    }

    public void setStatus(JobVacancyStatusType status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JobVacancy that = (JobVacancy) o;

        if (id != that.id) return false;
        if (organizationId != that.organizationId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (uploadDate != null ? !uploadDate.equals(that.uploadDate) : that.uploadDate != null) return false;
        if (requirement != null ? !requirement.equals(that.requirement) : that.requirement != null) return false;
        return status == that.status;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + organizationId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (uploadDate != null ? uploadDate.hashCode() : 0);
        result = 31 * result + (requirement != null ? requirement.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "JobVacancy{" +
                "id=" + id +
                ", organizationId=" + organizationId +
                ", name='" + name + '\'' +
                ", uploadDate='" + uploadDate + '\'' +
                ", requirement='" + requirement + '\'' +
                ", status=" + status +
                '}';
    }
}
