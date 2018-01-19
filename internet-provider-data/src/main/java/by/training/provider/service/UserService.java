package by.training.provider.service;

import by.training.provider.dao.exception.DataException;
import by.training.provider.dao.impl.UserDao;
import by.training.provider.entity.*;
import by.training.provider.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserService implements PersonService {

    /**
     * Get user excluding inner objects.
     *
     * @param email user email.
     * @return User.
     * @throws DataException
     */
    public User getUserByEmail(String email) throws DataException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        UserDao dao = new UserDao(connection);

        List<User> userList;
        try {
            userList = dao.getByUniqueParameter(email);
            connection.commit();
        } catch (SQLException e) {
            throw new DataException(e.getMessage(), e.getCause());
        } finally {
            pool.recycleConnection(connection);
        }

        User user = null;
        if (userList.size() > 1) {
            throw new DataException("Got more than one user by unique parameter.");
        } else {
            if (userList.size() == 1) {
                user = userList.iterator().next();
            }
        }

        return user;
    }

    /**
     * Get fully constructed User, including all inner objects.
     *
     * @param id user id.
     * @return User.
     * @throws DataException
     */
    public User getEagerUser(Integer id) throws DataException {
        User user = getUserById(id);

        PlanService planService = new PlanService();
        Integer planId = user.getPlanId();
        Plan plan = planService.getPlanById(planId);
        user.setPlan(plan);

        Integer userId = user.getId();

        TrafficService trafficService = new TrafficService();
        List<Traffic> trafficList = trafficService.getTrafficsByUserId(userId);
        user.setTrafficList(trafficList);

        PaymentService paymentService = new PaymentService();
        List<Payment> paymentList = paymentService.getPaymentsByUserId(userId);
        user.setPaymentList(paymentList);

        return user;
    }

    public void updateUser(User user) throws DataException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        UserDao dao = new UserDao(connection);

        try {
            dao.update(user);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DataException(e.getMessage(), e.getCause());
            }
            throw new DataException(e.getMessage(), e.getCause());
        } finally {
            pool.recycleConnection(connection);
        }
    }

    /**
     * Get all users excluding inner objects.
     *
     * @return List of Users.
     * @throws DataException
     */
    public List<User> getAllUsers() throws DataException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        UserDao dao = new UserDao(connection);

        List<User> userList;
        try {
            userList = dao.getAll();
            connection.commit();
        } catch (SQLException e) {
            throw new DataException(e.getMessage(), e.getCause());
        } finally {
            pool.recycleConnection(connection);
        }

        return userList;
    }

    /**
     * Get all users including only Plan from inner objects.
     *
     * @return List of Users.
     * @throws DataException
     */
    public List<User> getAllUsersWithPlan() throws DataException {
        List<User> userList = getAllUsers();

        PlanService planService = new PlanService();

        for (User user : userList) {
            Integer planId = user.getPlanId();
            Plan plan = planService.getPlanById(planId);
            user.setPlan(plan);
        }

        return userList;
    }

    /**
     * Get user excluding inner objects.
     *
     * @param id user id.
     * @return User.
     * @throws DataException
     */
    public User getUserById(Integer id) throws DataException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        UserDao dao = new UserDao(connection);

        User user;
        try {
            user = dao.getById(id);
            connection.commit();
        } catch (SQLException e) {
            throw new DataException(e.getMessage(), e.getCause());
        } finally {
            pool.recycleConnection(connection);
        }

        return user;
    }

    @Override
    public Person getPersonByEmail(String email) throws DataException {
        return getUserByEmail(email);
    }

    /**
     * Checks if User with given email already exists.
     *
     * @param email user email.
     * @return true when exists, false otherwise.
     * @throws DataException
     */
    public boolean isUserExists(String email) throws DataException {
        User user = getUserByEmail(email);

        return user != null;
    }
}
