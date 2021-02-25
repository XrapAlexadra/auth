package edu.epam.auth.model.ad;

import java.util.List;

public interface CategoryComponent {

    Category getCategory();

    void setCategory(Category category);

    public List<CategoryComponent> getCategoryComponents();

    public void addCategoryComponent(CategoryComponent categoryComponent);

}
