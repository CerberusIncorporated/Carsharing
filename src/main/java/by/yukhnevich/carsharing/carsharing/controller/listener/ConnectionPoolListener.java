package by.yukhnevich.carsharing.carsharing.controller.listener;

import by.yukhnevich.carsharing.carsharing.model.connection.ConnectionPool;
import by.yukhnevich.carsharing.carsharing.model.connection.exception.ConnectionPoolException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebListener
public class ConnectionPoolListener implements ServletContextListener {

    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Initializes connection pool when application is starting
     * @see ConnectionPool
     * */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ConnectionPool.getInstance().initPoolData();
        } catch (ConnectionPoolException e) {
            LOGGER.fatal("Can't initialize pool data.");
            throw new RuntimeException(e);
        }
    }

    /**
     * Disposing the connection pool when application is finished
     * @see ConnectionPool
     * */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.getInstance().dispose();
    }


}
