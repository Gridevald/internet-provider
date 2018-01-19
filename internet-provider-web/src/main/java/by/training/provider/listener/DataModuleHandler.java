package by.training.provider.listener;

import by.training.provider.pool.ConnectionPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DataModuleHandler implements ServletContextListener {

    /**
     * Initializes connection pool when web application initialization
     * process is starting.
     *
     * @param sce the ServletContextEvent containing the ServletContext
     * that is being initialized
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ConnectionPool.initPool();
    }

    /**
     * Destroys connection pool when ServletContext is about to be
     * shut down.
     *
     * @param sce the ServletContextEvent containing the ServletContext
     * that is being destroyed
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.destroyPool();
    }
}
