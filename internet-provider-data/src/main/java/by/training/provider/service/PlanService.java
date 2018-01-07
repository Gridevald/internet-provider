package by.training.provider.service;

import by.training.provider.dao.exception.DataException;
import by.training.provider.dao.impl.PlanDao;
import by.training.provider.entity.Plan;
import by.training.provider.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PlanService {

    public List<Plan> getAllPlans() throws DataException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        PlanDao dao = new PlanDao(connection);

        List<Plan> planList;
        try {
            planList = dao.getAll();
            connection.commit();
        } catch (SQLException e) {
            throw new DataException(e.getMessage(), e.getCause());
        } finally {
            pool.recycleConnection(connection);
        }

        return planList;
    }

    public Plan getById(Integer id) throws DataException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        PlanDao dao = new PlanDao(connection);

        Plan plan;
        try {
            plan = dao.getById(id);
        } catch (DataException e) {
            throw new DataException(e.getMessage(), e.getCause());
        } finally {
            pool.recycleConnection(connection);
        }

        return plan;
    }

    public void inserPlan(Plan plan) throws DataException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        PlanDao dao = new PlanDao(connection);

        try {
            dao.insert(plan);
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
