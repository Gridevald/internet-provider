package by.training.provider.loader;

import java.util.Properties;

public class DbConfigCreator {

    private static final String DRIVER = "driver";
    private static final String URL = "url";
    private static final String POOL_SIZE = "pool_size";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    /**
     * Creates DbConfig object.
     *
     * @param properties with DB configuration.
     * @return DbConfig object.
     */
    public DbConfig create(Properties properties) {
        DbConfig dbConfig = new DbConfig();

        String driver = properties.getProperty(DRIVER);
        dbConfig.setDriver(driver);

        String url = properties.getProperty(URL);
        dbConfig.setUrl(url);

        String poolSizeStr = properties.getProperty(POOL_SIZE);
        int poolSize = Integer.valueOf(poolSizeStr);
        dbConfig.setPoolSize(poolSize);

        String user = properties.getProperty(USER);
        dbConfig.setUser(user);

        String password = properties.getProperty(PASSWORD);
        dbConfig.setPassword(password);

        return dbConfig;
    }
}
