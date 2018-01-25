package by.training.provider.dao.impl;

import by.training.provider.dao.exception.DataException;
import by.training.provider.dao.util.DateUtil;
import by.training.provider.entity.Payment;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDao extends AbstractDao<Payment> {

    /**
     * Column names.
     */
    private static final String ID = "id";
    private static final String SUM = "sum";
    private static final String DATE = "date";
    private static final String USER_ID = "user_id";

    /**
     * Query for selecting all records from payment table.
     */
    private static final String SELECT_QUERY = "SELECT " +
            "payment.id, payment.sum, payment.date, payment.user_id " +
            "FROM internet_provider.payment";

    /**
     * Where user ID postfix.
     */
    private static final String UNIQUE = " WHERE payment.user_id = ?";

    /**
     * Where id postfix.
     */
    private static final String WHERE_ID = " WHERE payment.id = ?";

    /**
     * Query for updating records in payment table.
     */
    private static final String UPDATE_QUERY = "UPDATE " +
            "internet_provider.payment " +
            "SET payment.sum = ?, payment.date = ?, payment.user_id = ? " +
            "WHERE payment.id = ?";

    /**
     * Query for deleting record from payment table.
     */
    private static final String DELETE_QUERY = "DELETE FROM " +
            "internet_provider.payment WHERE payment.id = ?";

    /**
     * Query for inserting records in payment table.
     */
    private static final String INSERT_QUERY = "INSERT INTO " +
            "internet_provider.payment " +
            "(payment.sum, payment.date, payment.user_id) " +
            "VALUES (?, ?, ?)";

    public PaymentDao(Connection connection) {
        super(connection);
    }

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
    protected List<Payment> parseResultSet(ResultSet resultSet)
            throws DataException {

        List<Payment> paymentList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Payment payment = new Payment();

                Integer id = resultSet.getInt(ID);
                payment.setId(id);

                BigDecimal sum = resultSet.getBigDecimal(SUM);
                payment.setSum(sum);

                java.sql.Date dateSql = resultSet.getDate(DATE);
                java.util.Date dateJava = DateUtil.sqlToJavaDate(dateSql);
                payment.setDate(dateJava);

                Integer userId = resultSet.getInt(USER_ID);
                payment.setUserId(userId);

                paymentList.add(payment);
            }
        } catch (SQLException e) {
            throw new DataException(e.getMessage(), e.getCause());
        }

        return paymentList;
    }

    @Override
    protected void prepareForUpdate(PreparedStatement statement, Payment element)
            throws DataException {

        try {
            BigDecimal sum = element.getSum();
            statement.setBigDecimal(1, sum);

            java.util.Date dateJava = element.getDate();
            java.sql.Date dateSql = DateUtil.javaToSqlDate(dateJava);
            statement.setDate(2, dateSql);

            Integer userId = element.getUserId();
            statement.setInt(3, userId);

            Integer id = element.getId();
            statement.setInt(4, id);
        } catch (SQLException e) {
            throw new DataException(e.getMessage(), e.getCause());
        }
    }

    @Override
    protected void prepareForInsert(PreparedStatement statement, Payment element)
            throws DataException {

        try {
            BigDecimal sum = element.getSum();
            statement.setBigDecimal(1, sum);

            java.util.Date dateJava = element.getDate();
            java.sql.Date dateSql = DateUtil.javaToSqlDate(dateJava);
            statement.setDate(2, dateSql);

            Integer userId = element.getUserId();
            statement.setInt(3, userId);
        } catch (SQLException e) {
            throw new DataException(e.getMessage(), e.getCause());
        }
    }
}
