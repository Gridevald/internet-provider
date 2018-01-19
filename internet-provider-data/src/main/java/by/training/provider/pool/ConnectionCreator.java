package by.training.provider.pool;

import by.training.provider.loader.DbConfig;
import by.training.provider.loader.DbConfigReader;
import by.training.provider.pool.exception.PoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionCreator {

    private static final Logger LOGGER = LogManager.getLogger();
    private String url;
    private String user;
    private String password;
    private String driver;

    public ConnectionCreator() {
        init();
    }

    /**
     * Creates new Connection.
     *
     * @return Connection
     * @throws PoolException when SQLException occurred.
     */
    public Connection getConnection() throws PoolException {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            connection.setAutoCommit(false);

            return connection;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new PoolException(e.getMessage(), e.getCause());
        }
    }

    /**
     * Initializing by getting DB Config from DbConfig Object.
     *
     * @throws PoolException when ClassNotFoundException occurred on
     * registering driver class.
     */
    private void init() throws PoolException {
        DbConfigReader reader = new DbConfigReader();
        DbConfig dbConfig = reader.readProperties();

        url = dbConfig.getUrl();
        user = dbConfig.getUser();
        password = dbConfig.getPassword();
        driver = dbConfig.getDriver();

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
            throw new PoolException(e.getMessage(), e.getCause());
        }
    }
}
