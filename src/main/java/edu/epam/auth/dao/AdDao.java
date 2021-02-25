package edu.epam.auth.dao;

import edu.epam.auth.exception.DaoException;
import edu.epam.auth.model.ad.Ad;

import java.util.List;

public abstract class AdDao extends AbstractDao<Long, Ad> {

    abstract public long save(Ad ad) throws DaoException;

    abstract public List<Ad> findAll() throws DaoException;

}
