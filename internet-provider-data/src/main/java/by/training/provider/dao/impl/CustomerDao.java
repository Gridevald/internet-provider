package by.training.provider.dao.impl;

import by.training.provider.dao.exception.DataException;
import by.training.provider.entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao extends AbstractDao<Customer> {

    /**
     * Column names.
     */
    private static final String ID = "id";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String FIRST_NAME = "first_name";
    private static final String MIDDLE_NAME = "middle_name";
    private static final String LAST_NAME = "last_name";
    private static final String CITY = "city";
    private static final String STREET = "street";
    private static final String BUILDING = "building";
    private static final String APARTMENTS = "apartments";
    private static final String PLAN_ID = "plan_id";

    /**
     * Query for selecting all records from customer table.
     */
    private static final String SELECT_QUERY = "SELECT " +
            "customer.id, customer.email, customer.password, " +
            "customer.first_name, customer.middle_name, " +
            "customer.last_name, customer.city, customer.street, " +
            "customer.building, customer.apartments, customer.plan_id " +
            "FROM internet_provider.customer";

    /**
     * Where name postfix.
     */
    private static final String UNIQUE = " WHERE customer.email = ?";

    /**
     * Where id postfix.
     */
    private static final String WHERE_ID = " WHERE customer.id = ?";

    /**
     * Query for updating records in customer table.
     */
    private static final String UPDATE = "UPDATE " +
            "internet_provider.customer " +
            "SET customer.email = ?, customer.password = ?, " +
            "customer.first_name = ?, customer.middle_name = ?, " +
            "customer.last_name = ?, customer.city = ?, " +
            "customer.street = ?, customer.building = ?, " +
            "customer.apartments = ?, customer.plan_id = ? " +
            "WHERE customer.id = ?";

    /**
     * Query for deleting record from customer table.
     */
    private static final String DELETE_QUERY = "DELETE FROM " +
            "internet_provider.customer WHERE customer.id = ?";

    /**
     * Query for inserting records in customer table.
     */
    private static final String INSERT_QUERY = "INSERT INTO " +
            "internet_provider.customer " +
            "(customer.email, customer.password, customer.first_name, " +
            "customer.middle_name, customer.last_name, customer.city, " +
            "customer.street, customer.building, customer.apartments, " +
            "customer.plan_id) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    //////////////////////////////////////////////////////////////////////

    public CustomerDao(Connection connection) {
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
        return UPDATE;
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
    protected List<Customer> parseResultSet(ResultSet resultSet)
            throws DataException {

        List<Customer> customerList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Customer customer = new Customer();

                Integer id = resultSet.getInt(ID);
                customer.setId(id);

                String email = resultSet.getString(EMAIL);
                customer.setEmail(email);

                String password = resultSet.getString(PASSWORD);
                customer.setPassword(password);

                String firstName = resultSet.getString(FIRST_NAME);
                customer.setFirstName(firstName);

                String middleName = resultSet.getString(MIDDLE_NAME);
                customer.setMiddleName(middleName);

                String lastName = resultSet.getString(LAST_NAME);
                customer.setLastName(lastName);

                String city = resultSet.getString(CITY);
                customer.setCity(city);

                String street = resultSet.getString(STREET);
                customer.setStreet(street);

                String building = resultSet.getString(BUILDING);
                customer.setBuilding(building);

                Integer apartments = resultSet.getInt(APARTMENTS);
                customer.setApartments(apartments);

                Integer planId = resultSet.getInt(PLAN_ID);
                customer.setPlanId(planId);

                customerList.add(customer);
            }
        } catch (SQLException e) {
            throw new DataException(e.getMessage(), e.getCause());
        }

        return customerList;
    }

    @Override
    protected void prepareForUpdate(PreparedStatement statement, Customer element)
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

            String city = element.getCity();
            statement.setString(6, city);

            String street = element.getStreet();
            statement.setString(7, street);

            String building = element.getBuilding();
            statement.setString(8, building);

            Integer apartments = element.getApartments();
            statement.setInt(9, apartments);

            Integer planId = element.getPlanId();
            statement.setInt(10, planId);

            Integer id = element.getId();
            statement.setInt(11, id);
        } catch (SQLException e) {
            throw new DataException(e.getMessage(), e.getCause());
        }
    }

    @Override
    protected void prepareForInsert(PreparedStatement statement, Customer element)
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

            String city = element.getCity();
            statement.setString(6, city);

            String street = element.getStreet();
            statement.setString(7, street);

            String building = element.getBuilding();
            statement.setString(8, building);

            Integer apartments = element.getApartments();
            statement.setInt(9, apartments);

            Integer planId = element.getPlanId();
            statement.setInt(10, planId);
        } catch (SQLException e) {
            throw new DataException(e.getMessage(), e.getCause());
        }
    }
}
