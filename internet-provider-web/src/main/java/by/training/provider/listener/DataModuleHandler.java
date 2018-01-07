package by.training.provider.listener;

import by.training.provider.pool.ConnectionPool;
import by.training.provider.pool.exception.PoolException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DataModuleHandler implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ConnectionPool.initPool();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.destroyPool();
    }
}
