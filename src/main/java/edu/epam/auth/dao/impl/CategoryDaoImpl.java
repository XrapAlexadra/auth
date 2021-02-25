package edu.epam.auth.dao.impl;

import edu.epam.auth.dao.CategoryDao;
import edu.epam.auth.exception.DaoException;
import edu.epam.auth.model.ad.Category;
import edu.epam.auth.service.impl.UserServiceImpl;
import edu.epam.auth.util.DaoUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryDaoImpl extends CategoryDao {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private static final String INSERT = "INSERT INTO categories (name, up_id) VALUES (?, ?);";
    private static final String FIND_ALL ="SELECT c.name, c.id, c.up_id FROM categories AS c;";
    private static final String FIND_BY_NAME = "SELECT c.name, c.id, c.up_id FROM categories AS c WHERE name=?;";
    private static final String FIND_BY_ID = "SELECT c.name, c.id, c.up_id FROM categories AS c WHERE c.id=?;";
    private static final String DELETE = "DELETE FROM categories WHERE id=?;";
    private static final String UPDATE = "UPDATE categories as c SET c.name=? WHERE id=?;";

    public CategoryDaoImpl() {
    }

    @Override
    public long save(Category category) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement
                (INSERT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, category.getName());
            statement.setLong(2, category.getUpCategoryId());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                generatedKeys.next();
                return generatedKeys.getLong(1);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
    @Override
    public List<Category> findAll() throws DaoException {
        List<Category> categoryList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL)) {
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Category category = new Category();
                    category.setId(rs.getInt("id"));
                    category.setName(rs.getString("name"));
                    category.setUpCategoryId(rs.getLong("up_id"));
                    categoryList.add(category);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return categoryList;
    }

    @Override
    public Optional<Category> findByName(String name) throws DaoException {
        Optional<Category> categoryFromBd = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(FIND_BY_NAME)) {
            statement.setString(1, name);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    Category category = new Category();
                    category.setId(rs.getInt("id"));
                    category.setName(rs.getString("name"));
                    category.setUpCategoryId(rs.getLong("up_id"));
                    categoryFromBd = Optional.of(category);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return categoryFromBd;
    }

    @Override
    public Optional<Category> findById(Long id) throws DaoException {
        Optional<Category> categoryFromBd = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
            statement.setLong(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    Category category = new Category();
                    category.setId(rs.getInt("id"));
                    category.setName(rs.getString("name"));
                    category.setUpCategoryId(rs.getLong("up_id"));
                    categoryFromBd = Optional.of(category);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return categoryFromBd;
    }

    @Override
    public void update(Category category) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setString(1, category.getName());
            statement.setLong(2, category.getId());
            int affectedRows = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean deleteById(Long id) throws DaoException {
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setLong(1, id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows != 0) {
                result = true;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }
}
