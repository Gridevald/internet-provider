package by.training.provider.service;

import by.training.provider.dao.exception.DataException;
import by.training.provider.dao.impl.TrafficDao;
import by.training.provider.entity.Traffic;
import by.training.provider.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TrafficService {

    public List<Traffic> getTrafficsByUserId(Integer userId) throws DataException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        TrafficDao dao = new TrafficDao(connection);

        List<Traffic> trafficList;
        try {
            trafficList = dao.getByUniqueParameter(String.valueOf(userId));
            connection.commit();
        } catch (DataException | SQLException e) {
            throw new DataException(e.getMessage(), e.getCause());
        } finally {
            pool.recycleConnection(connection);
        }

        return trafficList;
    }
}
