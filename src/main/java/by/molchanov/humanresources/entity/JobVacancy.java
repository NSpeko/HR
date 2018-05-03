package by.molchanov.humanresources.entity;

import java.util.Comparator;

public class JobVacancy {
    private int id;
    private int organizationId;
    private String name;
    private String uploadDate;
    private String requirement;
    private JobVacancyStatusType status;
    private String organizationName;
    private String organizationWebsite;

    public JobVacancy() {
    }

    public JobVacancy(int organizationId, String name, String uploadDate, String requirement, JobVacancyStatusType status) {
        this.organizationId = organizationId;
        this.name = name;
        this.uploadDate = uploadDate;
        this.requirement = requirement;
        this.status = status;
    }

    public JobVacancy(int id, int organizationId, String name, String uploadDate, String requirement, JobVacancyStatusType status, String organizationName, String organizationWebsite) {
        this.id = id;
        this.organizationId = organizationId;
        this.name = name;
        this.uploadDate = uploadDate;
        this.requirement = requirement;
        this.status = status;
        this.organizationName = organizationName;
        this.organizationWebsite = organizationWebsite;
    }

    public static Comparator<JobVacancy> SORT_BY_NAME = new Comparator<JobVacancy>() {
        @Override
        public int compare(JobVacancy o1, JobVacancy o2) {
            int res = String.CASE_INSENSITIVE_ORDER.compare(o1.getName(), o2.getName());
            if (res == 0) {

                
            }
        }
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

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationWebsite() {
        return organizationWebsite;
    }

    public void setOrganizationWebsite(String organizationWebsite) {
        this.organizationWebsite = organizationWebsite;
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
        if (status != that.status) return false;
        if (organizationName != null ? !organizationName.equals(that.organizationName) : that.organizationName != null)
            return false;
        return organizationWebsite != null ? organizationWebsite.equals(that.organizationWebsite) : that.organizationWebsite == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + organizationId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (uploadDate != null ? uploadDate.hashCode() : 0);
        result = 31 * result + (requirement != null ? requirement.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (organizationName != null ? organizationName.hashCode() : 0);
        result = 31 * result + (organizationWebsite != null ? organizationWebsite.hashCode() : 0);
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
                ", organizationName='" + organizationName + '\'' +
                ", organizationWebsite='" + organizationWebsite + '\'' +
                '}';
    }
}
