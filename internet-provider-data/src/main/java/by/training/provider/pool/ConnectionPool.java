package by.training.provider.pool;

import by.training.provider.loader.DbConfig;
import by.training.provider.loader.DbConfigReader;
import by.training.provider.loader.exception.LoaderException;
import by.training.provider.pool.exception.PoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

public class ConnectionPool {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final ConnectionPool INSTANCE = new ConnectionPool();
    private static final BlockingDeque<Connection> POOL = new LinkedBlockingDeque<>();
    private static final ConnectionCreator creator = new ConnectionCreator();
    private static AtomicBoolean isInitialized = new AtomicBoolean(false);

    private ConnectionPool() {
    }

    public static ConnectionPool getInstance() {
        return INSTANCE;
    }

    /**
     * Pool initialization. Adds connections according to dbConfig file
     * if it still not initialized.
     *
     * @throws PoolException when LoaderException occurred.
     */
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
                LOGGER.error(e.getMessage());
                throw new PoolException(e.getMessage(), e.getCause());
            }
        }
    }

    /**
     * Pool destruction. Removes all connections from pool and closes them.
     *
     * @throws PoolException when SQLException occurred on connection closing.
     */
    public static void destroyPool() throws PoolException {
        if (isInitialized.get()) {
            try {
                for (int i = 0; i < POOL.size(); i++) {
                    Connection connection = POOL.poll();
                    connection.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                throw new PoolException(e.getMessage(), e.getCause());
            }
        }
    }

    /**
     * Takes connection if exists from pool, otherwise waits while connection
     * appears in pool. If connection is valid, then returns this connection,
     * otherwise returns new connection.
     *
     * @return Connection.
     * @throws PoolException when InterruptedException occurred on taking
     * connection from BlockingDeque (pool base) or
     * when SQLException occurred on checking connection validity.
     */
    public Connection getConnection() throws PoolException {
        try {
            Connection connection = POOL.take();
            if (!connection.isValid(1)) {
                connection = creator.getConnection();
            }
            return connection;
        } catch (InterruptedException | SQLException e) {
            LOGGER.error(e.getMessage());
            throw new PoolException(e.getMessage(), e.getCause());
        }
    }

    /**
     * Recycles connection if it is valid, otherwise put new connection.
     *
     * @param connection Connection to recycle.
     * @throws PoolException when InterruptedException occurred on putting
     * connection into BlockingDeque (pool base) or
     * when SQLException occurred on checking connection validity.
     */
    public void recycleConnection(Connection connection) throws PoolException {
        try {
            if (connection == null || !connection.isValid(1)) {
                connection = creator.getConnection();
            }
            POOL.put(connection);
        } catch (InterruptedException | SQLException e) {
            LOGGER.error(e.getMessage());
            throw new PoolException(e.getMessage(), e.getCause());
        }
    }
}
