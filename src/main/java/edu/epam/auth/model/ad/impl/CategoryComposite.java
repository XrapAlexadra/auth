package edu.epam.auth.model.ad.impl;

import edu.epam.auth.model.ad.Category;
import edu.epam.auth.model.ad.CategoryComponent;

import java.util.ArrayList;
import java.util.List;

public class CategoryComposite implements CategoryComponent {

    private Category category;
    private List<CategoryComponent> categoryComponents;

    public CategoryComposite(Category category) {
        this.category = category;
        categoryComponents = new ArrayList<>();
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<CategoryComponent> getCategoryComponents() {
        return new ArrayList<>(categoryComponents);
    }

    public void addCategoryComponent(CategoryComponent categoryComponent) {
       categoryComponents.add(categoryComponent);
    }



}
