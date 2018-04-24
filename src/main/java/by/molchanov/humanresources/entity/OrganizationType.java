package by.molchanov.humanresources.entity;

public enum OrganizationType {
    COMMERCIAL("commercial"), NONCOMMERCIAL("non-commercial");

    private String value;

    OrganizationType(String description) {
        this.value = description;
    }

    public String getValue() {
        return value;
    }
}
