package edu.epam.auth.dao;

import edu.epam.auth.exception.DaoException;
import edu.epam.auth.model.ad.Category;

import java.util.List;
import java.util.Optional;

public abstract class CategoryDao extends AbstractDao<Long, Category> {

    abstract public long save(Category category) throws DaoException;

    abstract public List<Category> findAll() throws DaoException;

    abstract public Optional<Category> findByName(String name) throws DaoException;
}
