package edu.epam.auth.util;

import edu.epam.auth.model.ad.Ad;
import edu.epam.auth.model.ad.Category;
import edu.epam.auth.model.ad.CategoryComponent;
import edu.epam.auth.model.ad.impl.CategoryComposite;
import edu.epam.auth.model.ad.impl.CategoryLeaf;

import java.util.List;
import java.util.Optional;

public class CatalogBuilder {

    public static Optional<CategoryComponent> build(List<Category> categories){
        Category currentCategory = findUpperCategory(categories);
        CategoryComponent result = null;
        if(currentCategory != null){
            result = build(currentCategory, categories);
        }
        return Optional.ofNullable(result);
    }
    public static Optional<CategoryComponent> build(List<Category> categories, List<Ad> ads){
        Category currentCategory = findUpperCategory(categories);
        CategoryComponent result = null;
        if(currentCategory != null){
            result = build(currentCategory, categories, ads);
        }
        return Optional.ofNullable(result);
    }

    private static Category findUpperCategory(List<Category> categories){
        Category currentCategory = null;
        int i = 0;
        while (i < categories.size()){
            Category category = categories.get(i);
            if(category.getUpCategoryId() == 0){
                currentCategory = category;
                break;
            }
        }
        return currentCategory;
    }

    private static CategoryComponent build(Category currentCategory, List<Category> categories, List<Ad> ads){
        CategoryComponent result;
        result = new CategoryComposite(currentCategory);
        long currentCategoryId = currentCategory.getId();
        for (Category category : categories) {
            if (category.getUpCategoryId() == currentCategoryId) {
                CategoryComponent nestedComponent = build(category, categories, ads);
                result.addCategoryComponent(nestedComponent);
            }
        }
        for(Ad ad: ads){
            Category adCategory = ad.getCategory();
            if(adCategory.getId() == currentCategoryId) {
                CategoryComponent adComponent = new CategoryLeaf(ad);
                result.addCategoryComponent(adComponent);
            }
        }
        return result;
    }
    public static CategoryComponent build(Category currentCategory, List<Category> categories){
        CategoryComponent result;
        result = new CategoryComposite(currentCategory);
        long currentCategoryId = currentCategory.getId();
        for (Category category : categories) {
            if (category.getUpCategoryId() == currentCategoryId) {
                CategoryComponent nestedComponent = build(category, categories);
                result.addCategoryComponent(nestedComponent);
            }
        }
        return result;
    }
}
