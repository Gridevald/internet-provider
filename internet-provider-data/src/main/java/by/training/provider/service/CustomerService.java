package by.training.provider.service;

import by.training.provider.dao.exception.DataException;
import by.training.provider.dao.impl.CustomerDao;
import by.training.provider.entity.Customer;
import by.training.provider.entity.Person;
import by.training.provider.entity.Plan;
import by.training.provider.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CustomerService implements PersonService {

    /**
     * Get customer excluding Plan object.
     *
     * @param email customer email.
     * @return Customer.
     * @throws DataException
     */
    public Customer getCustomerByEmail(String email) throws DataException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        CustomerDao dao = new CustomerDao(connection);

        List<Customer> customerList;
        try {
            customerList = dao.getByUniqueParameter(email);
            connection.commit();
        } catch (SQLException e) {
            throw new DataException(e.getMessage(), e.getCause());
        } finally {
            pool.recycleConnection(connection);
        }

        Customer customer = null;
        if (customerList.size() > 1) {
            throw new DataException("Got more than one customer by unique parameter.");
        } else {
            if (customerList.size() == 1) {
                customer = customerList.iterator().next();
            }
        }

        return customer;
    }

    public void addCustomer(Customer customer) throws DataException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        CustomerDao dao = new CustomerDao(connection);

        try {
            dao.insert(customer);
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

    /**
     * Get customer excluding Plan object.
     *
     * @return List of Customers.
     * @throws DataException
     */
    public List<Customer> getAllCustomers() throws DataException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        CustomerDao dao = new CustomerDao(connection);

        List<Customer> customerList;
        try {
            customerList = dao.getAll();
            connection.commit();
        } catch (SQLException e) {
            throw new DataException(e.getMessage(), e.getCause());
        } finally {
            pool.recycleConnection(connection);
        }

        return customerList;
    }

    /**
     * Get customer including Plan object.
     *
     * @return List of Customers.
     * @throws DataException
     */
    public List<Customer> getAllCustomersWithPlan() throws DataException {
        List<Customer> customerList = getAllCustomers();

        PlanService planService = new PlanService();

        for (Customer customer : customerList) {
            Integer planId = customer.getPlanId();
            Plan plan = planService.getPlanById(planId);
            customer.setPlan(plan);
        }

        return customerList;
    }

    /**
     * Get customer including Plan object.
     *
     * @param id customer id.
     * @return Customer.
     * @throws DataException
     */
    public Customer getEagerCustomerById(Integer id) throws DataException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        CustomerDao dao = new CustomerDao(connection);

        Customer customer;
        try {
            customer = dao.getById(id);
            connection.commit();
        } catch (SQLException e) {
            throw new DataException(e.getMessage(), e.getCause());
        } finally {
            pool.recycleConnection(connection);
        }

        PlanService planService = new PlanService();

        Integer planId = customer.getPlanId();
        Plan plan = planService.getPlanById(planId);
        customer.setPlan(plan);

        return customer;
    }

    @Override
    public Person getPersonByEmail(String email) throws DataException {
        return getCustomerByEmail(email);
    }
}
