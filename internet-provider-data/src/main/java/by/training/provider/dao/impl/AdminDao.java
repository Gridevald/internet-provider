package by.training.provider.dao.impl;

import by.training.provider.dao.exception.DataException;
import by.training.provider.entity.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDao
        extends AbstractDao<Admin> {

    /**
     * Column names.
     */
    private static final String ID = "id";
    private static final String PERSONNEL_NUMBER = "personnel_number";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String FIRST_NAME = "first_name";
    private static final String MIDDLE_NAME = "middle_name";
    private static final String LAST_NAME = "last_name";

    /**
     * Query for selecting all records from admin table.
     */
    private static final String SELECT_QUERY = "SELECT " +
            "admin.id, admin.personnel_number, admin.email, " +
            "admin.password, admin.first_name, " +
            "admin.middle_name, admin.last_name " +
            "FROM internet_provider.admin";

    /**
     * Where email postfix.
     */
    private static final String UNIQUE = " WHERE admin.email = ?";

    /**
     * Where id postfix.
     */
    private static final String WHERE_ID = " WHERE admin.id = ?";

    /**
     * Query for updating records in admin table.
     */
    private static final String UPDATE_QUERY = "UPDATE " +
            "internet_provider.admin " +
            "SET admin.personnel_number = ?, admin.email = ?, " +
            "admin.password = ?, admin.first_name = ?," +
            "admin.middle_name = ?, admin.last_name = ? " +
            "WHERE admin.id = ?";

    /**
     * Query for deleting record from admin table.
     */
    private static final String DELETE_QUERY = "DELETE FROM " +
            "internet_provider.admin WHERE admin.id = ?";

    /**
     * Query for inserting records in admin table.
     */
    private static final String INSERT_QUERY = "INSERT INTO " +
            "internet_provider.admin " +
            "(admin.personnel_number, admin.email, " +
            "admin.password, admin.first_name, " +
            "admin.middle_name, admin.last_name) " +
            "VALUES (?, ?, ?, ?, ?, ?)";

    //////////////////////////////////////////////////////////////////////

    public AdminDao(Connection connection) {
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
    protected List<Admin> parseResultSet(ResultSet resultSet)
            throws DataException {

        List<Admin> adminList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Admin admin = new Admin();

                Integer id = resultSet.getInt(ID);
                admin.setId(id);

                Integer personnelNumber = resultSet.getInt(PERSONNEL_NUMBER);
                admin.setPersonnelNumber(personnelNumber);

                String email = resultSet.getString(EMAIL);
                admin.setEmail(email);

                String password = resultSet.getString(PASSWORD);
                admin.setPassword(password);

                String firstName = resultSet.getString(FIRST_NAME);
                admin.setFirstName(firstName);

                String middleName = resultSet.getString(MIDDLE_NAME);
                admin.setMiddleName(middleName);

                String lastName = resultSet.getString(LAST_NAME);
                admin.setLastName(lastName);

                adminList.add(admin);
            }
        } catch (SQLException e) {
            throw new DataException(e.getMessage(), e.getCause());
        }

        return adminList;
    }

    @Override
    protected void prepareForUpdate(PreparedStatement statement, Admin element)
            throws DataException {

        try {
            Integer personnelNumber = element.getPersonnelNumber();
            statement.setInt(1, personnelNumber);

            String email = element.getEmail();
            statement.setString(2, email);

            String password = element.getPassword();
            statement.setString(3, password);

            String firstName = element.getFirstName();
            statement.setString(4, firstName);

            String middleName = element.getMiddleName();
            statement.setString(5, middleName);

            String lastName = element.getLastName();
            statement.setString(6, lastName);

            Integer id = element.getId();
            statement.setInt(7, id);
        } catch (SQLException e) {
            throw new DataException(e.getMessage(), e.getCause());
        }
    }

    @Override
    protected void prepareForInsert(PreparedStatement statement, Admin element)
            throws DataException {

        try {
            Integer personnelNumber = element.getPersonnelNumber();
            statement.setInt(1, personnelNumber);

            String email = element.getEmail();
            statement.setString(2, email);

            String password = element.getPassword();
            statement.setString(3, password);

            String firstName = element.getFirstName();
            statement.setString(4, firstName);

            String middleName = element.getMiddleName();
            statement.setString(5, middleName);

            String lastName = element.getLastName();
            statement.setString(6, lastName);
        } catch (SQLException e) {
            throw new DataException(e.getMessage(), e.getCause());
        }
    }
}
