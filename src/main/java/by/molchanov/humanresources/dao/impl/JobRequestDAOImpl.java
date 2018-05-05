package by.molchanov.humanresources.dao.impl;

import by.molchanov.humanresources.dao.AbstractDAO;
import by.molchanov.humanresources.dao.JobRequestDAO;
import by.molchanov.humanresources.entity.JobRequest;
import by.molchanov.humanresources.entity.JobRequestStatusType;
import by.molchanov.humanresources.exception.CustomDAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.molchanov.humanresources.constant.SQLQuery.*;

public class JobRequestDAOImpl extends AbstractDAO<JobRequest> implements JobRequestDAO {
    private static final JobRequestDAOImpl JOB_REQUEST_DAO = new JobRequestDAOImpl();

    private JobRequestDAOImpl() {

    }

    public static JobRequestDAOImpl getInstance() {
        return JOB_REQUEST_DAO;
    }

    @Override
    public String getSelectQueryBase() {
        return JOB_REQUEST_QUERY_SELECT;
    }

    @Override
    public String getUpdateQueryBase() {
        return JOB_REQUEST_QUERY_UPDATE;
    }

    @Override
    public String getDeleteQueryBase() {
        return JOB_REQUEST_QUERY_DELETE_BY_ID;
    }

    @Override
    public String getCreateQueryBase() {
        return JOB_REQUEST_QUERY_CREATE;
    }

    @Override
    public void preparedStatementForUpdate(PreparedStatement statement, JobRequest object) throws CustomDAOException {
        try {
            statement.setInt(1, object.getJobVacancyId());
            statement.setInt(2, object.getUserId());
            statement.setString(3, object.getResume());
            statement.setString(4, object.getStatus().getValue());
            statement.setInt(5, object.getId());
        } catch (SQLException e) {
            throw new CustomDAOException("Statement error for update!", e);
        }
    }

    @Override
    public void preparedStatementForDelete(PreparedStatement statement, JobRequest object) throws CustomDAOException {
        try {
            statement.setInt(1, object.getId());
        } catch (SQLException e) {
            throw new CustomDAOException("Statement error for delete!", e);
        }
    }

    @Override
    public void preparedStatementForInsert(PreparedStatement statement, JobRequest object) throws CustomDAOException {
        try {
            statement.setInt(1, object.getJobVacancyId());
            statement.setInt(2, object.getUserId());
            statement.setString(3, object.getResume());
            statement.setString(4, object.getStatus().getValue());
        } catch (SQLException e) {
            throw new CustomDAOException("Statement error for insert!", e);
        }
    }

    @Override
    public String getPKName() {
        return JOB_REQUEST_FIELD_ID;
    }

    @Override
    public List<JobRequest> parseResultSet(ResultSet set) throws CustomDAOException {
        List<JobRequest> result = new ArrayList<>();
        JobRequest jobRequest;
        try {
            while (set.next()) {
                jobRequest = new JobRequest();
                jobRequest.setId(set.getInt(JOB_REQUEST_FIELD_ID));
                jobRequest.setJobVacancyId(set.getInt(JOB_REQUEST_FIELD_JOB_VACANCY_ID));
                jobRequest.setUserId(set.getInt(JOB_REQUEST_FIELD_USER_ID));
                jobRequest.setResume(set.getString(JOB_REQUEST_FIELD_RESUME));
                jobRequest.setStatus(JobRequestStatusType.valueOf(set.getString(JOB_REQUEST_FIELD_STATUS).toUpperCase()));
                result.add(jobRequest);
            }
        } catch (SQLException e) {
            throw new CustomDAOException("Parsing result set error!", e);
        }
        return result;
    }

    @Override
    public boolean objectHasId(JobRequest object) {
        return object.getId() != 0;
    }
}
