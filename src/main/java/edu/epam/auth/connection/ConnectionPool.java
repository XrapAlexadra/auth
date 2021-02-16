package edu.epam.auth.connection;

import edu.epam.auth.exception.ConnectionPoolException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Enumeration;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static final int DEFAULT_POOL_SIZE = 12;

    private static ConnectionPool instance;
    private static AtomicBoolean instanceExist = new AtomicBoolean(false);
    private static ReentrantLock lock = new ReentrantLock(true);
    private DataSource dataSource = MariaDbDataSourceFactory.createSqlDataSource();

    private BlockingQueue<ProxyConnection> freeConnections;
    private Queue<ProxyConnection> givenAwayConnections;


    private ConnectionPool() {
        freeConnections = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
        createConnections(DEFAULT_POOL_SIZE);
        int freeConnectionSize = freeConnections.size();
        if (freeConnectionSize == 0) {
            throw new RuntimeException("No connections to DataBase.");
        }
        givenAwayConnections = new ArrayDeque<>();
    }

    private void createConnections(int connectionNumber) {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                ProxyConnection connection = new ProxyConnection(dataSource.getConnection());
                freeConnections.add(connection);
            } catch (SQLException e) {
//                logger
            }
        }
    }

    public static ConnectionPool getInstance() {
        if (instanceExist.compareAndSet(false, true)) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            givenAwayConnections.offer(connection);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    public void releaseConnection(Connection connection) throws ConnectionPoolException {
        if (connection instanceof ProxyConnection) {
            givenAwayConnections.remove(connection);
            freeConnections.offer((ProxyConnection) connection);
        } else {
            throw new ConnectionPoolException("This connection wouldn't return to pool. Because it isn't from ConnectionPool.");
        }
    }

    public void destroyPool() {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                freeConnections.take().reallyClose();
            } catch (SQLException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        deregisterDrivers();
    }

    private void deregisterDrivers() {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            try {
                Driver driver = drivers.nextElement();
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
