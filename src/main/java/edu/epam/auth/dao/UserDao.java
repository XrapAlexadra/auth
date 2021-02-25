package edu.epam.auth.dao;

import edu.epam.auth.exception.DaoException;
import edu.epam.auth.model.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public abstract class UserDao extends AbstractDao<Long, User>{

    public abstract long save(User user, String password) throws DaoException;

    public abstract Optional<User> findByLogin(String login) throws DaoException;

    public abstract Optional<String> findPasswordByLogin(String login) throws DaoException;

    public abstract List<User> findAll()throws DaoException;

    public abstract int deleteMoreThenInactive(LocalDate date)throws DaoException;

    public abstract void updatePassword(String login, String newPassword) throws DaoException;

    public abstract List<User> findNumberFrom(Long from, int number) throws DaoException;

    public abstract Long getTotalNumber() throws DaoException;
}
