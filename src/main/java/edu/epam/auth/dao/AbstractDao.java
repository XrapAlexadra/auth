package edu.epam.auth.dao;

import edu.epam.auth.exception.DaoException;
import edu.epam.auth.model.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao <K, T extends Entity> {

    private static final Logger logger = LogManager.getLogger(AbstractDao.class);

    protected Connection connection;

    public abstract Optional<T> findById(K k) throws DaoException;

    public abstract void update(T t) throws DaoException;

    public abstract void deleteById(K k) throws DaoException;

    public abstract List<T> findNumberFrom(K from, int number) throws DaoException;

    public abstract K getTotalNumber() throws DaoException;

    public void close(Statement statement){
        try {
            if(statement != null){
                statement.close();
            }
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    void setConnection(Connection connection){
        this.connection = connection;
    }
}
