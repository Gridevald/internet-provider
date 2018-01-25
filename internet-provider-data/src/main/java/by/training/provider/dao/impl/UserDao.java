package by.training.provider.dao.impl;

import by.training.provider.dao.exception.DataException;
import by.training.provider.entity.User;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends AbstractDao<User> {

    /**
     * Column names.
     */
    private static final String ID = "id";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String FIRST_NAME = "first_name";
    private static final String MIDDLE_NAME = "middle_name";
    private static final String LAST_NAME = "last_name";
    private static final String CONTRACT = "contract";
    private static final String BALANCE = "balance";
    private static final String IS_BLOCKED = "is_blocked";
    private static final String CITY = "city";
    private static final String STREET = "street";
    private static final String BUILDING = "building";
    private static final String APARTMENTS = "apartments";
    private static final String PLAN_ID = "plan_id";

    /**
     * Query for selecting all records from user table.
     */
    private static final String SELECT_QUERY = "SELECT " +
            "user.id, user.email, user.password, user.first_name, " +
            "user.middle_name, user.last_name, user.contract, " +
            "user.balance, user.is_blocked, user.city, user.street, " +
            "user.building, user.apartments, user.plan_id " +
            "FROM internet_provider.user";

    /**
     * Where name postfix.
     */
    private static final String UNIQUE = " WHERE user.email = ?";

    /**
     * Where id postfix.
     */
    private static final String WHERE_ID = " WHERE user.id = ?";

    /**
     * Query for updating records in user table.
     */
    private static final String UPDATE_QUERY = "UPDATE " +
            "internet_provider.user " +
            "SET user.email = ?, user.password = ?, user.first_name = ?, " +
            "user.middle_name = ?, user.last_name = ?, user.contract = ?, " +
            "user.balance = ?, user.is_blocked = ?, user.city = ?, " +
            "user.street = ?, user.building = ?, " +
            "user.apartments = ?, user.plan_id = ? " +
            "WHERE user.id = ?";

    /**
     * Query for deleting record from user table.
     */
    private static final String DELETE_QUERY = "DELETE FROM " +
            "internet_provider.user WHERE user.id = ?";

    /**
     * Query for inserting records in user table.
     */
    private static final String INSERT_QUERY = "INSERT INTO " +
            "internet_provider.user " +
            "(user.email, user.password, user.first_name, " +
            "user.middle_name, user.last_name, user.contract, " +
            "user.balance, user.is_blocked, user.city, user.street, " +
            "user.building, user.apartments, user.plan_id) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public UserDao(Connection connection) {
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
    protected List<User> parseResultSet(ResultSet resultSet)
            throws DataException {

        List<User> userList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                User user = new User();

                Integer id = resultSet.getInt(ID);
                user.setId(id);

                String email = resultSet.getString(EMAIL);
                user.setEmail(email);

                String password = resultSet.getString(PASSWORD);
                user.setPassword(password);

                String firstName = resultSet.getString(FIRST_NAME);
                user.setFirstName(firstName);

                String middleName = resultSet.getString(MIDDLE_NAME);
                user.setMiddleName(middleName);

                String lastName = resultSet.getString(LAST_NAME);
                user.setLastName(lastName);

                Integer contract = resultSet.getInt(CONTRACT);
                user.setContract(contract);

                BigDecimal balance = resultSet.getBigDecimal(BALANCE);
                user.setBalance(balance);

                Byte isBlocked = resultSet.getByte(IS_BLOCKED);
                user.setIsBlocked(isBlocked);

                String city = resultSet.getString(CITY);
                user.setCity(city);

                String street = resultSet.getString(STREET);
                user.setStreet(street);

                String building = resultSet.getString(BUILDING);
                user.setBuilding(building);

                Integer apartments = resultSet.getInt(APARTMENTS);
                user.setApartments(apartments);

                Integer planId = resultSet.getInt(PLAN_ID);
                user.setPlanId(planId);

                userList.add(user);
            }
        } catch (SQLException e) {
            throw new DataException(e.getMessage(), e.getCause());
        }

        return userList;
    }

    @Override
    protected void prepareForUpdate(PreparedStatement statement, User element)
            throws DataException {

        try {
            String email = element.getEmail();
            statement.setString(1, email);

            String password = element.getPassword();
            statement.setString(2, password);

            String firstName = element.getFirstName();
            statement.setString(3, firstName);

            String middleName = element.getMiddleName();
            statement.setString(4, middleName);

            String lastName = element.getLastName();
            statement.setString(5, lastName);

            Integer contract = element.getContract();
            statement.setInt(6, contract);

            BigDecimal balance = element.getBalance();
            statement.setBigDecimal(7, balance);

            Byte isBlocked = element.getIsBlocked();
            statement.setByte(8, isBlocked);

            String city = element.getCity();
            statement.setString(9, city);

            String street = element.getStreet();
            statement.setString(10, street);

            String building = element.getBuilding();
            statement.setString(11, building);

            Integer apartments = element.getApartments();
            statement.setInt(12, apartments);

            Integer planId = element.getPlanId();
            statement.setInt(13, planId);

            Integer id = element.getId();
            statement.setInt(14, id);
        } catch (SQLException e) {
            throw new DataException(e.getMessage(), e.getCause());
        }
    }

    @Override
    protected void prepareForInsert(PreparedStatement statement, User element)
            throws DataException {

        try {
            String email = element.getEmail();
            statement.setString(1, email);

            String password = element.getPassword();
            statement.setString(2, password);

            String firstName = element.getFirstName();
            statement.setString(3, firstName);

            String middleName = element.getMiddleName();
            statement.setString(4, middleName);

            String lastName = element.getLastName();
            statement.setString(5, lastName);

            Integer contract = element.getContract();
            statement.setInt(6, contract);

            BigDecimal balance = element.getBalance();
            statement.setBigDecimal(7, balance);

            Byte isBlocked = element.getIsBlocked();
            statement.setByte(8, isBlocked);

            String city = element.getCity();
            statement.setString(9, city);

            String street = element.getStreet();
            statement.setString(10, street);

            String building = element.getBuilding();
            statement.setString(11, building);

            Integer apartments = element.getApartments();
            statement.setInt(12, apartments);

            Integer planId = element.getPlanId();
            statement.setInt(13, planId);
        } catch (SQLException e) {
            throw new DataException(e.getMessage(), e.getCause());
        }
    }
}
