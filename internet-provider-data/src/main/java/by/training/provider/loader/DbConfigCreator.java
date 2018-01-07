package by.training.provider.loader;

import java.util.Properties;

public class DbConfigCreator {

    private static final String DRIVER = "driver";
    private static final String URL = "url";
    private static final String POOL_SIZE = "pool_size";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    public DbConfig create(Properties properties) {
        DbConfig infoEntity = new DbConfig();

        String driver = properties.getProperty(DRIVER);
        infoEntity.setDriver(driver);

        String url = properties.getProperty(URL);
        infoEntity.setUrl(url);

        String poolSizeStr = properties.getProperty(POOL_SIZE);
        int poolSize = Integer.valueOf(poolSizeStr);
        infoEntity.setPoolSize(poolSize);

        String user = properties.getProperty(USER);
        infoEntity.setUser(user);

        String password = properties.getProperty(PASSWORD);
        infoEntity.setPassword(password);

        return infoEntity;
    }
}
