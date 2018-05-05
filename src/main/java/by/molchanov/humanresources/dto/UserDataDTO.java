package by.molchanov.humanresources.dto;

import by.molchanov.humanresources.entity.Organization;
import by.molchanov.humanresources.entity.User;

public class UserDataDTO {
    private User userExemplar;
    private Organization userOrganizationInfo;
    private String infoMessage;
    private String email;
    private String password;
    private String repeatPassword;
    private String firstName;
    private String lastName;
    private String role;

    public User getUserExemplar() {
        return userExemplar;
    }

    public void setUserExemplar(User userExemplar) {
        this.userExemplar = userExemplar;
    }

    public String getInfoMessage() {
        return infoMessage;
    }

    public void setInfoMessage(String infoMessage) {
        this.infoMessage = infoMessage;
    }

    public Organization getUserOrganizationInfo() {
        return userOrganizationInfo;
    }

    public void setUserOrganizationInfo(Organization userOrganizationInfo) {
        this.userOrganizationInfo = userOrganizationInfo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
