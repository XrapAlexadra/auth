package edu.epam.auth.service;

import edu.epam.auth.exception.ServiceException;
import edu.epam.auth.model.ad.Category;
import edu.epam.auth.model.ad.CategoryComponent;

import java.util.Optional;

public interface CategoryService {

    String addCategory(String name, long upCategoryId) throws ServiceException;

    CategoryComponent buildCategoryTree() throws ServiceException;

    Optional<Category> findById(long id) throws ServiceException;

    String deleteById(long id) throws ServiceException;

    String updateName(long categoryId, String name)throws ServiceException;
}
