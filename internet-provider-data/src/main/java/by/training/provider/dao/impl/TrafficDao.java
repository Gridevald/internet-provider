package by.training.provider.dao.impl;

import by.training.provider.dao.exception.DataException;
import by.training.provider.dao.util.DateUtil;
import by.training.provider.entity.Traffic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrafficDao extends AbstractDao<Traffic> {

    /**
     * Column names.
     */
    private static final String ID = "id";
    private static final String DOWNLOADED = "downloaded";
    private static final String UPLOADED = "uploaded";
    private static final String DATE = "date";
    private static final String USER_ID = "user_id";

    /**
     * Query for selecting all records from traffic table.
     */
    private static final String SELECT_QUERY = "SELECT " +
            "traffic.id, traffic.downloaded, traffic.uploaded, " +
            "traffic.date, traffic.user_id " +
            "FROM internet_provider.traffic";

    /**
     * Where user ID postfix.
     */
    private static final String UNIQUE = " WHERE traffic.user_id = ?";

    /**
     * Where id postfix.
     */
    private static final String WHERE_ID = " WHERE traffic.id = ?";

    /**
     * Query for updating records in traffic table.
     */
    private static final String UPDATE_QUERY = "UPDATE " +
            "internet_provider.traffic " +
            "SET traffic.downloaded = ?, traffic.uploaded = ?, " +
            "traffic.date = ?, traffic.user_id = ? " +
            "WHERE traffic.id = ?";

    /**
     * Query for deleting record from traffic table.
     */
    private static final String DELETE_QUERY = "DELETE FROM " +
            "internet_provider.traffic WHERE traffic.id = ?";

    /**
     * Query for inserting records in traffic table.
     */
    private static final String INSERT_QUERY = "INSERT INTO " +
            "internet_provider.traffic " +
            "(traffic.downloaded, traffic.uploaded, " +
            "traffic.date, traffic.user_id) " +
            "VALUES (?, ?, ?, ?)";

    //////////////////////////////////////////////////////////////////////

    public TrafficDao(Connection connection) {
        super(connection);
    }

    //////////////////////////////////////////////////////////////////////

    @Override
    protected String getSelectQuery() {
        return SELECT_QUERY;
    }

    @Override
    protected String getUniqueQuery() {
        return SELECT_QUERY + UNIQUE;
    }

    @Override
    protected String getWhereIdQuery() {
        return SELECT_QUERY + WHERE_ID;
    }

    @Override
    protected String getUpdateQuery() {
        return UPDATE_QUERY;
    }

    @Override
    protected String getDeleteQuery() {
        return DELETE_QUERY;
    }

    @Override
    protected String getInsertQuery() {
        return INSERT_QUERY;
    }

    @Override
    protected List<Traffic> parseResultSet(ResultSet resultSet)
            throws DataException {

        List<Traffic> trafficList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Traffic traffic = new Traffic();

                Integer id = resultSet.getInt(ID);
                traffic.setId(id);

                Double downloaded = resultSet.getDouble(DOWNLOADED);
                traffic.setDownloaded(downloaded);

                Double uploaded = resultSet.getDouble(UPLOADED);
                traffic.setUploaded(uploaded);

                java.sql.Date dateSql = resultSet.getDate(DATE);
                java.util.Date dateJava = DateUtil.sqlToJavaDate(dateSql);
                traffic.setDate(dateJava);

                Integer userId = resultSet.getInt(USER_ID);
                traffic.setUserId(userId);

                trafficList.add(traffic);
            }
        } catch (SQLException e) {
            throw new DataException(e.getMessage(), e.getCause());
        }

        return trafficList;
    }

    @Override
    protected void prepareForUpdate(PreparedStatement statement, Traffic element)
            throws DataException {

        try {
            Double downloaded = element.getDownloaded();
            statement.setDouble(1, downloaded);

            Double uploaded = element.getUploaded();
            statement.setDouble(2, uploaded);

            java.util.Date dateJava = element.getDate();
            java.sql.Date dateSql = DateUtil.javaToSqlDate(dateJava);
            statement.setDate(3, dateSql);

            Integer userId = element.getUserId();
            statement.setInt(4, userId);

            Integer id = element.getId();
            statement.setInt(5, id);
        } catch (SQLException e) {
            throw new DataException(e.getMessage(), e.getCause());
        }
    }

    @Override
    protected void prepareForInsert(PreparedStatement statement, Traffic element)
            throws DataException {

        try {
            Double downloaded = element.getDownloaded();
            statement.setDouble(1, downloaded);

            Double uploaded = element.getUploaded();
            statement.setDouble(2, uploaded);

            java.util.Date dateJava = element.getDate();
            java.sql.Date dateSql = DateUtil.javaToSqlDate(dateJava);
            statement.setDate(3, dateSql);

            Integer userId = element.getUserId();
            statement.setInt(4, userId);
        } catch (SQLException e) {
            throw new DataException(e.getMessage(), e.getCause());
        }
    }
}
