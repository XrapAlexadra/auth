package edu.epam.auth.dao;

import edu.epam.auth.exception.DaoException;
import edu.epam.auth.model.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserDao extends BaseDao<Long, User>{

    long save(User user, String password) throws DaoException;

    Optional<User> findByLogin(String login) throws DaoException;

    Optional<String> findPasswordByLogin(String login) throws DaoException;

    List<User> findAll()throws DaoException;

    int deleteMoreThenInactive(LocalDate date)throws DaoException;

    void updatePassword(String login, String newPassword) throws DaoException;
}
