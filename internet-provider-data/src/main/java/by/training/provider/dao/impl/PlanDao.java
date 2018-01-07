package by.training.provider.dao.impl;

import by.training.provider.dao.exception.DataException;
import by.training.provider.entity.Plan;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlanDao extends AbstractDao<Plan> {

    /**
     * Column names.
     */
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String DOWNLOAD_SPEED = "download_speed";
    private static final String UPLOAD_SPEED = "upload_speed";
    private static final String PRICE = "price";
    private static final String DESCRIPTION = "description";

    /**
     * Query for selecting all records from plan table.
     */
    private static final String SELECT_QUERY = "SELECT " +
            "plan.id, plan.name, plan.download_speed, " +
            "plan.upload_speed, plan.price, plan.description " +
            "FROM internet_provider.plan";

    /**
     * Where name postfix.
     */
    private static final String UNIQUE = " WHERE plan.name = ?";

    /**
     * Where id postfix.
     */
    private static final String WHERE_ID = " WHERE plan.id = ?";

    /**
     * Query for updating records in plan table.
     */
    private static final String UPDATE_QUERY = "UPDATE " +
            "internet_provider.plan " +
            "SET plan.name = ?, plan.download_speed = ?, " +
            "plan.upload_speed = ?, plan.price = ?, plan.description = ? " +
            "WHERE plan.id = ?";

    /**
     * Query for deleting record from plan table.
     */
    private static final String DELETE_QUERY = "DELETE FROM " +
            "internet_provider.plan WHERE plan.id = ?";

    /**
     * Query for inserting records in plan table.
     */
    private static final String INSERT_QUERY = "INSERT INTO " +
            "internet_provider.plan " +
            "(plan.name, plan.download_speed, plan.upload_speed, " +
            "plan.price, plan.description) " +
            "VALUES (?, ?, ?, ?, ?)";

    //////////////////////////////////////////////////////////////////////

    public PlanDao(Connection connection) {
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
    protected List<Plan> parseResultSet(ResultSet resultSet)
            throws DataException {

        List<Plan> planList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Plan plan = new Plan();

                Integer id = resultSet.getInt(ID);
                plan.setId(id);

                String name = resultSet.getString(NAME);
                plan.setName(name);

                Integer downloadSpeed = resultSet.getInt(DOWNLOAD_SPEED);
                plan.setDownloadSpeed(downloadSpeed);

                Integer uploadSpeed = resultSet.getInt(UPLOAD_SPEED);
                plan.setUploadSpeed(uploadSpeed);

                BigDecimal price = resultSet.getBigDecimal(PRICE);
                plan.setPrice(price);

                String description = resultSet.getString(DESCRIPTION);
                plan.setDescription(description);

                planList.add(plan);
            }
        } catch (SQLException e) {
            throw new DataException(e.getMessage(), e.getCause());
        }

        return planList;
    }

    @Override
    protected void prepareForUpdate(PreparedStatement statement, Plan element)
            throws DataException {

        try {
            String name = element.getName();
            statement.setString(1, name);

            Integer downloadSpeed = element.getDownloadSpeed();
            statement.setInt(2, downloadSpeed);

            Integer uploadSpeed = element.getUploadSpeed();
            statement.setInt(3, uploadSpeed);

            BigDecimal price = element.getPrice();
            statement.setBigDecimal(4, price);

            String description = element.getDescription();
            statement.setString(5, description);

            Integer id = element.getId();
            statement.setInt(6, id);
        } catch (SQLException e) {
            throw new DataException(e.getMessage(), e.getCause());
        }
    }

    @Override
    protected void prepareForInsert(PreparedStatement statement, Plan element)
            throws DataException {

        try {
            String name = element.getName();
            statement.setString(1, name);

            Integer downloadSpeed = element.getDownloadSpeed();
            statement.setInt(2, downloadSpeed);

            Integer uploadSpeed = element.getUploadSpeed();
            statement.setInt(3, uploadSpeed);

            BigDecimal price = element.getPrice();
            statement.setBigDecimal(4, price);

            String description = element.getDescription();
            statement.setString(5, description);;
        } catch (SQLException e) {
            throw new DataException(e.getMessage(), e.getCause());
        }
    }
}
