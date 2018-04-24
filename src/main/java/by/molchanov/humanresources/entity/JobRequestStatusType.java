package by.molchanov.humanresources.entity;

public enum JobRequestStatusType {
    REJECTED("rejected"), NEW("new"), APPROVED("approved");

    private String value;

    JobRequestStatusType(String description) {
        this.value = description;
    }

    public String getValue() {
        return value;
    }
}
