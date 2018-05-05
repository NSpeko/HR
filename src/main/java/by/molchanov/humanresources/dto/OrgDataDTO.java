package by.molchanov.humanresources.dto;

import by.molchanov.humanresources.entity.Organization;
import by.molchanov.humanresources.entity.User;

public class OrgDataDTO {
    private User orgDirector;
    private Organization organizationExemplar;
    private String directorRole;
    private String name;
    private String website;
    private String description;
    private String type;
    private String infoMessage;

    public User getOrgDirector() {
        return orgDirector;
    }

    public void setOrgDirector(User orgDirector) {
        this.orgDirector = orgDirector;
    }

    public Organization getOrganizationExemplar() {
        return organizationExemplar;
    }

    public void setOrganizationExemplar(Organization organizationExemplar) {
        this.organizationExemplar = organizationExemplar;
    }

    public String getDirectorRole() {
        return directorRole;
    }

    public void setDirectorRole(String directorRole) {
        this.directorRole = directorRole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInfoMessage() {
        return infoMessage;
    }

    public void setInfoMessage(String infoMessage) {
        this.infoMessage = infoMessage;
    }
}
