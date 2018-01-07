package by.training.provider.service;

import by.training.provider.dao.exception.DataException;
import by.training.provider.dao.impl.PaymentDao;
import by.training.provider.dao.impl.PlanDao;
import by.training.provider.dao.impl.TrafficDao;
import by.training.provider.dao.impl.UserDao;
import by.training.provider.entity.*;
import by.training.provider.pool.ConnectionPool;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserService implements PersonService {

    public User getUserByUnique(String email) throws DataException {
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

    public User getEagerUser(Integer id) throws DataException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        UserDao userDao = new UserDao(connection);

        User user = userDao.getById(id);

        PlanService planService = new PlanService();
        Integer planId = user.getPlanId();
        Plan plan = planService.getById(planId);
        user.setPlan(plan);

        Integer userId = user.getId();

        TrafficService trafficService = new TrafficService();
        List<Traffic> trafficList = trafficService.getTrafficsByUserId(userId);
        user.setTrafficList(trafficList);

        PaymentService paymentService = new PaymentService();
        List<Payment> paymentList = paymentService.getPaymentsByUserId(userId);
        user.setPaymentList(paymentList);

        try {
            connection.commit();
        } catch (SQLException e) {
            throw new DataException(e.getMessage(), e.getCause());
        } finally {
            pool.recycleConnection(connection);
        }

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

    public List<User> getAllLazyUsers() throws DataException {
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

    public List<User> getAllUsersWithPlan() throws DataException {
        List<User> userList = getAllLazyUsers();

        PlanService planService = new PlanService();

        for (User user : userList) {
            Integer planId = user.getPlanId();
            Plan plan = planService.getById(planId);
            user.setPlan(plan);
        }

        return userList;
    }

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
        return getUserByUnique(email);
    }

    public boolean isUserExists(String email) throws DataException {
        User user = getUserByUnique(email);

        return user != null;
    }
}
