package by.training.provider.service;

import by.training.provider.dao.exception.DataException;
import by.training.provider.dao.impl.CustomerDao;
import by.training.provider.dao.impl.UserDao;
import by.training.provider.entity.Customer;
import by.training.provider.entity.User;
import by.training.provider.pool.ConnectionPool;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

public class ApproveUserService {

    public void moveCustomerToUser(Customer customer, Integer contract) throws DataException {

        if (customer == null) {
            throw new DataException("customer is null.");
        }

        User user = new User();

        String firstName = customer.getFirstName();
        user.setFirstName(firstName);

        String middleName = customer.getMiddleName();
        user.setMiddleName(middleName);

        String lastName = customer.getLastName();
        user.setLastName(lastName);

        String email = customer.getEmail();
        user.setEmail(email);

        String password = customer.getPassword();
        user.setPassword(password);

        String city = customer.getCity();
        user.setCity(city);

        String street = customer.getStreet();
        user.setStreet(street);

        String building = customer.getBuilding();
        user.setBuilding(building);

        Integer apartments = customer.getApartments();
        user.setApartments(apartments);

        Integer planId = customer.getPlanId();
        user.setPlanId(planId);

        user.setContract(contract);
        user.setBalance(new BigDecimal(0));
        user.setIsBlocked((byte) 0);

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        CustomerDao customerDao = new CustomerDao(connection);
        UserDao userDao = new UserDao(connection);

        try {
            userDao.insert(user);
            customerDao.delete(customer);
            connection.commit();
        } catch (SQLException | DataException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    throw new DataException(e1.getMessage(), e1.getCause());
                }
            }
        } finally {
            pool.recycleConnection(connection);
        }
    }
}
