package by.molchanov.humanresources.constant;

public class SQLQuery {
    public static final String USER_QUERY_SELECT = "SELECT u_id, u_email, u_role, u_password, u_firstname," +
            " u_lastname, u_organization_id FROM user ";
    public static final String USER_QUERY_UPDATE = "UPDATE user SET u_email = ?, u_role = ?, u_password = ?, u_firstname = ?," +
            " u_secondname = ?, u_organization_id = ? WHERE u_id = '?'";
    public static final String USER_QUERY_DELETE_BY_ID = "DELETE FROM user WHERE u_id = ?";
    public static final String USER_QUERY_CREATE = "INSERT INTO user (u_email, u_role, u_password, u_firstname," +
            " u_lastname, u_organization_id) VALUES (?, ?, ?, ?, ?, ?)";
    public static final String USER_FIELD_ID = "u_id";
    public static final String USER_FIELD_EMAIL = "u_email";
    public static final String USER_FIELD_PASS = "u_password";
    public static final String USER_FIELD_FIRST_NAME = "u_firstname";
    public static final String USER_FIELD_LAST_NAME = "u_lastname";
    public static final String USER_FIELD_ORGANIZATION_ID = "u_organization_id";
    public static final String USER_FIELD_ROLE = "u_role";


    public static final String ORGANIZATION_QUERY_SELECT = "SELECT o_id, o_name, o_description, o_website, o_type FROM organization ";
    public static final String ORGANIZATION_QUERY_UPDATE = "UPDATE organization SET o_name = ?, o_website = ?, o_description = ?, o_type = ? WHERE o_id = ";
    public static final String ORGANIZATION_QUERY_DELETE_BY_ID = "DELETE FROM organization WHERE o_id = ?";
    public static final String ORGANIZATION_QUERY_CREATE = "INSERT INTO organization (o_name, o_website, o_description, o_type) VALUES (?, ?, ?, ?)";
    public static final String ORGANIZATION_FIELD_ID = "o_id";
    public static final String ORGANIZATION_FIELD_NAME = "o_name";
    public static final String ORGANIZATION_FIELD_WEBSITE = "o_website";
    public static final String ORGANIZATION_FIELD_DESCRIPTION = "o_description";
    public static final String ORGANIZATION_FIELD_TYPE = "o_type";


    public static final String JOB_REQUEST_QUERY_SELECT = "SELECT jr_id, jr_job_vacancy_id, jr_user_id, jr_resume, jr_status FROM job_request ";
    public static final String JOB_REQUEST_QUERY_UPDATE = "UPDATE job_request SET jr_job_vacancy_id = ?, jr_user_id = ?," +
            " jr_resume = ?, jr_status = ? WHERE jr_id = ?";
    public static final String JOB_REQUEST_QUERY_DELETE_BY_ID = "DELETE FROM job_request WHERE jr_id = ?";
    public static final String JOB_REQUEST_QUERY_CREATE = "INSERT INTO job_request (jr_job_vacancy_id, jr_user_id, jr_resume, jr_status)" +
            " VALUES (?, ?, ?, ?)";
    public static final String JOB_REQUEST_FIELD_ID = "jr_id";
    public static final String JOB_REQUEST_FIELD_JOB_VACANCY_ID = "jr_job_vacancy_id";
    public static final String JOB_REQUEST_FIELD_USER_ID = "jr_user_id";
    public static final String JOB_REQUEST_FIELD_RESUME = "jr_resume";
    public static final String JOB_REQUEST_FIELD_STATUS = "jr_status";


    public static final String JOB_VACANCY_QUERY_SELECT = "SELECT jv_id, jv_organization_id, jv_name, jv_upload_date, jv_requirement, jv_status FROM job_vacancy ";
    public static final String JOB_VACANCY_QUERY_UPDATE = "UPDATE job_vacancy SET jv_organization_id = ?, jv_name = ?, jv_upload_date = ?," +
            " jv_requirement = ?, jv_status = ? WHERE jv_id = ?";
    public static final String JOB_VACANCY_QUERY_DELETE_BY_ID = "DELETE FROM job_vacancy WHERE jv_id = ?";
    public static final String JOB_VACANCY_QUERY_CREATE = "INSERT INTO job_vacancy (jv_organization_id, jv_name, jv_upload_date, jv_requirement, jv_status)" +
            " VALUES (?, ?, ?, ?, ?);";
    public static final String JOB_VACANCY_FIELD_ID = "jv_id";
    public static final String JOB_VACANCY_FIELD_ORGANIZATION_ID = "jv_organization_id";
    public static final String JOB_VACANCY_FIELD_NAME = "jv_name";
    public static final String JOB_VACANCY_FIELD_UPLOAD_DATE = "jv_upload_date";
    public static final String JOB_VACANCY_FIELD_REQUIREMENT = "jv_requirement";
    public static final String JOB_VACANCY_FIELD_STATUS = "jv_status";

    private SQLQuery() {}
}
