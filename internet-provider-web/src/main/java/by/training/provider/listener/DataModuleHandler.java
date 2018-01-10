package by.training.provider.listener;

import by.training.provider.pool.ConnectionPool;

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
