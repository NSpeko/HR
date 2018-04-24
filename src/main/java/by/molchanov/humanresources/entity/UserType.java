package by.molchanov.humanresources.entity;

public enum UserType {
    ADMIN("admin"), ASPIRANT("aspirant"), WORKER("worker");

    private String value;

    UserType(String description) {
        this.value = description;
    }

    public String getValue() {
        return value;
    }
}
