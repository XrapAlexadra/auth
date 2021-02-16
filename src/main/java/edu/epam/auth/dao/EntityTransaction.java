package edu.epam.auth.dao;

import edu.epam.auth.connection.ConnectionPool;
import edu.epam.auth.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class EntityTransaction {

    private static final Logger logger = LogManager.getLogger(EntityTransaction.class);


    private Connection connection;

    private final ConnectionPool pool = ConnectionPool.getInstance();

    public void begin(AbstractDao ... daos){
        if(connection == null){
            connection = pool.getConnection();
        }
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (AbstractDao dao : daos){
            dao.setConnection(connection);
        }
    }

    public void end(){
        try{
            connection.setAutoCommit(true);
            pool.releaseConnection(connection);
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
        }

        connection = null;
    }

    public void commit(){
        try{
            connection.commit();
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    public void rollback(){
        try{
            connection.rollback();
        } catch (SQLException e) {
            logger.error(e);
        }
    }
}
