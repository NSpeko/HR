package by.molchanov.humanresources.dao.impl;

import by.molchanov.humanresources.dao.AbstractDAO;
import by.molchanov.humanresources.dao.JobVacancyDAO;
import by.molchanov.humanresources.entity.JobVacancy;
import by.molchanov.humanresources.entity.JobVacancyStatusType;
import by.molchanov.humanresources.exception.CustomDAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static by.molchanov.humanresources.constant.SQLQuery.*;

public class JobVacancyDAOImpl extends AbstractDAO<JobVacancy> implements JobVacancyDAO {
    private static final JobVacancyDAOImpl JOB_REQUEST_DAO = new JobVacancyDAOImpl();

    private JobVacancyDAOImpl() {}

    public static JobVacancyDAOImpl getInstance() {
        return JOB_REQUEST_DAO;
    }

    @Override
    public String getSelectQueryBase() {
        return JOB_VACANCY_QUERY_SELECT;
    }

    @Override
    public String getUpdateQueryBase() {
        return JOB_VACANCY_QUERY_UPDATE;
    }

    @Override
    public String getDeleteQueryBase() {
        return JOB_VACANCY_QUERY_DELETE_BY_ID;
    }

    @Override
    public String getCreateQueryBase() {
        return JOB_VACANCY_QUERY_CREATE;
    }

    @Override
    public void preparedStatementForUpdate(PreparedStatement statement, JobVacancy object) throws CustomDAOException {
        try {
            statement.setInt(1, object.getOrganizationId());
            statement.setString(2, object.getName());
            statement.setString(4, object.getRequirement());
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
            object.setUploadDate(timeStamp);
            statement.setString(3, timeStamp);
            statement.setString(5, object.getStatus().getValue());
            statement.setInt(6, object.getId());
        } catch (SQLException e) {
            throw new CustomDAOException("Statement error for update!", e);
        }
    }

    @Override
    public void preparedStatementForDelete(PreparedStatement statement, JobVacancy object) throws CustomDAOException {
        try {
            statement.setInt(1, object.getId());
        } catch (SQLException e) {
            throw new CustomDAOException("Statement error for delete!", e);
        }
    }

    @Override
    public void preparedStatementForInsert(PreparedStatement statement, JobVacancy object) throws CustomDAOException {
        try {
            statement.setInt(1, object.getOrganizationId());
            statement.setString(2, object.getName());
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
            object.setUploadDate(timeStamp);
            statement.setString(3, timeStamp);
            statement.setString(4, object.getRequirement());
            statement.setString(5, object.getStatus().getValue());
        } catch (SQLException e) {
            throw new CustomDAOException("Statement error for insert!", e);
        }
    }

    @Override
    public String getPKName() {
        return JOB_VACANCY_FIELD_ID;
    }

    @Override
    public List<JobVacancy> parseResultSet(ResultSet set) throws CustomDAOException {
        List<JobVacancy> result = new ArrayList<>();
        JobVacancy jobVacancy;
        try {
            while (set.next()) {
                jobVacancy = new JobVacancy();
                jobVacancy.setId(set.getInt(JOB_VACANCY_FIELD_ID));
                jobVacancy.setName(set.getString(JOB_VACANCY_FIELD_NAME));
                jobVacancy.setOrganizationId(set.getInt(JOB_VACANCY_FIELD_ORGANIZATION_ID));
                jobVacancy.setUploadDate(set.getString(JOB_VACANCY_FIELD_UPLOAD_DATE));
                jobVacancy.setRequirement(set.getString(JOB_VACANCY_FIELD_REQUIREMENT));
                jobVacancy.setStatus(JobVacancyStatusType.valueOf(set.getString(JOB_VACANCY_FIELD_STATUS).toUpperCase()));
                result.add(jobVacancy);
            }
        } catch (SQLException e) {
            throw new CustomDAOException("Parsing result set error!", e);
        }
        return result;
    }

    @Override
    public boolean objectHasId(JobVacancy object) {
        return object.getId() != 0;
    }
}
