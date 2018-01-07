package by.training.provider.pool;

import by.training.provider.loader.DbConfig;
import by.training.provider.loader.DbConfigReader;
import by.training.provider.loader.exception.LoaderException;
import by.training.provider.pool.exception.PoolException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

public class ConnectionPool {

    private static final ConnectionPool INSTANCE = new ConnectionPool();
    private static final BlockingDeque<Connection> POOL = new LinkedBlockingDeque<>();
    private static final ConnectionCreator creator = new ConnectionCreator();
    private static AtomicBoolean isInitialized = new AtomicBoolean(false);

    private ConnectionPool() {
    }

    public static ConnectionPool getInstance() {
        return INSTANCE;
    }

    public static void initPool() throws PoolException {
        if (!isInitialized.get()) {
            try {
                DbConfigReader reader = new DbConfigReader();
                DbConfig dbConfig = reader.readProperties();
                int poolSize = dbConfig.getPoolSize();

                for (int i = 0; i < poolSize; i++) {
                    Connection connection = creator.getConnection();
                    POOL.push(connection);
                }

                isInitialized.set(true);
            } catch (LoaderException e) {
                throw new PoolException(e.getMessage(), e.getCause());
            }
        }
    }

    public static void destroyPool() throws PoolException {
        if (isInitialized.get()) {
            try {
                for (int i = 0; i < POOL.size(); i++) {
                    Connection connection = POOL.poll();
                    connection.close();
                }
            } catch (SQLException e) {
                throw new PoolException(e.getMessage(), e.getCause());
            }
        }
    }

    public Connection getConnection() {
        try {
            Connection connection = POOL.take();
            if (!connection.isValid(0)) {
                connection = creator.getConnection();
            }
            return connection;
        } catch (InterruptedException | SQLException e) {
            throw new PoolException(e.getMessage(), e.getCause());
        }
    }

    public void recycleConnection(Connection connection) {
        try {
            if (connection == null || !connection.isValid(0)) {
                connection = creator.getConnection();
            }
            POOL.put(connection);
        } catch (InterruptedException | SQLException e) {
            throw new PoolException(e.getMessage(), e.getCause());
        }
    }
}
