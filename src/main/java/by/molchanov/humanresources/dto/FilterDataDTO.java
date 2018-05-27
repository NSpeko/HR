package by.molchanov.humanresources.dto;

public class FilterDataDTO {
    String sortColumn;
    String sortDirectionType;
    String searchField;
    String userRole;
    int orgId;

    public String getSortColumn() {
        return sortColumn;
    }

    public void setSortColumn(String sortColumn) {
        this.sortColumn = sortColumn;
    }

    public String getSortDirectionType() {
        return sortDirectionType;
    }

    public void setSortDirectionType(String sortDirectionType) {
        this.sortDirectionType = sortDirectionType;
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }
}
