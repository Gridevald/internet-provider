package by.training.provider.pool;

import by.training.provider.loader.DbConfig;
import by.training.provider.loader.DbConfigReader;
import by.training.provider.pool.exception.PoolException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionCreator {

    private String url;
    private String user;
    private String password;
    private String driver;

    public ConnectionCreator() {
        init();
    }

    public Connection getConnection() throws PoolException {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            connection.setAutoCommit(false);

            return connection;
        } catch (SQLException e) {
            throw new PoolException(e.getMessage(), e.getCause());
        }
    }

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
            throw new PoolException(e.getMessage(), e.getCause());
        }
    }
}
