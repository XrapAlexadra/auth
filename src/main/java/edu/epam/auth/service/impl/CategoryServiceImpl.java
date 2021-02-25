package edu.epam.auth.service.impl;

import edu.epam.auth.connection.EntityTransaction;
import edu.epam.auth.dao.CategoryDao;
import edu.epam.auth.dao.impl.CategoryDaoImpl;
import edu.epam.auth.exception.DaoException;
import edu.epam.auth.exception.ServiceException;
import edu.epam.auth.model.ad.Category;
import edu.epam.auth.model.ad.CategoryComponent;
import edu.epam.auth.service.CategoryService;
import edu.epam.auth.util.CatalogBuilder;
import edu.epam.auth.validator.CategoryValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static edu.epam.auth.constant.MessageConstant.*;

import java.util.List;
import java.util.Optional;

public class CategoryServiceImpl implements CategoryService {

    private static final Logger logger = LogManager.getLogger(CategoryServiceImpl.class);

    private static final CategoryService instance = new CategoryServiceImpl();

    private CategoryServiceImpl() {
    }

    public static CategoryService getInstance() {
        return instance;
    }

    @Override
    public String addCategory(String name, long upCategoryId) throws ServiceException {
        CategoryDao categoryDao = new CategoryDaoImpl();
        String result;
        if (CategoryValidator.isValidName(name)) {
            EntityTransaction transaction = new EntityTransaction();
            transaction.begin(categoryDao);
            try {
                Optional<Category> categoryFromBd = categoryDao.findByName(name);
                if (categoryFromBd.isPresent()) {
                    result = REPEATED_CATEGORY_NAME;
                } else {
                    Category category = new Category(name, upCategoryId);
                    categoryDao.save(category);
                    result = SUCCESSFULLY_ADD_RECORD;
                }
                transaction.commit();
            } catch (DaoException e) {
                logger.error(e);
                transaction.rollback();
                throw new ServiceException(e);
            } finally {
                transaction.end();
            }
        } else {
            result = INVALID_CATALOG_NAME;
        }
        return result;
    }

    @Override
    public CategoryComponent buildCategoryTree() throws ServiceException {
        CategoryDao categoryDao = new CategoryDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.begin(categoryDao);
        CategoryComponent categoryTree;
        try {
            List<Category> categories = categoryDao.findAll();
            transaction.commit();
            Optional<CategoryComponent> catalog = CatalogBuilder.build(categories);
            if (catalog.isPresent()) {
                categoryTree = catalog.get();
            } else {
                logger.error("Catalog builder didn't find upper category.");
                throw new ServiceException("Catalog builder didn't find upper category.");
            }
        } catch (DaoException e) {
            logger.error(e);
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
        return categoryTree;
    }

    @Override
    public Optional<Category> findById(long id) throws ServiceException {
        Optional<Category> categoryFromBd;
        CategoryDao categoryDao = new CategoryDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.begin(categoryDao);
        try {
            categoryFromBd = categoryDao.findById(id);
            transaction.commit();
        } catch (DaoException e) {
            logger.error(e);
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
        return categoryFromBd;
    }

    @Override
    public String deleteById(long id) throws ServiceException {
        String result = IMPOSSIBLE_DELETE_CATEGORY;
        CategoryDao categoryDao = new CategoryDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.begin(categoryDao);
        try {
            boolean resultDeleting = categoryDao.deleteById(id);
            if (resultDeleting) {
                result = SUCCESSFULLY_DELETE_RECORD;
            }
            transaction.commit();
        } catch (DaoException e) {
            logger.error(e);
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
        return result;
    }

    @Override
    public String updateName(long categoryId, String name) throws ServiceException {
        String result = INVALID_CATALOG_NAME;
        if (CategoryValidator.isValidName(name)) {
            CategoryDao categoryDao = new CategoryDaoImpl();
            EntityTransaction transaction = new EntityTransaction();
            transaction.begin(categoryDao);
            try {
                Optional<Category> categoryFromBd = categoryDao.findByName(name);
                if (categoryFromBd.isPresent()) {
                    result = REPEATED_CATEGORY_NAME;
                }else {
                    categoryFromBd = categoryDao.findById(categoryId);
                    if(categoryFromBd.isPresent()) {
                        Category category = categoryFromBd.get();
                        category.setName(name);
                        categoryDao.update(category);
                        result = SUCCESSFULLY_CHANGE_RECORD;
                    }
                    else {
                        result = NOT_FIND_RECORD;
                    }
                }
            } catch (DaoException e) {
                logger.error(e);
                transaction.rollback();
                throw new ServiceException(e);
            } finally {
                transaction.end();
            }
        }
        return result;
    }
}
