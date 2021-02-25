package edu.epam.auth.model.ad.impl;

import edu.epam.auth.model.ad.Ad;
import edu.epam.auth.model.ad.Category;
import edu.epam.auth.model.ad.CategoryComponent;

import java.util.List;

public class CategoryLeaf implements CategoryComponent {

    private Ad ad;

    public CategoryLeaf(Ad ad) {
        this.ad = ad;
    }

    @Override
    public Category getCategory() {
        return null;
    }

    @Override
    public void setCategory(Category category) {

    }

    @Override
    public List<CategoryComponent> getCategoryComponents() {
        return null;
    }

    @Override
    public void addCategoryComponent(CategoryComponent categoryComponent) {

    }
}
