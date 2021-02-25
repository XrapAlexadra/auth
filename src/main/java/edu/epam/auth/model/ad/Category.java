package edu.epam.auth.model.ad;

import edu.epam.auth.model.Entity;

public class Category extends Entity {

    private long id;
    private String name;
    private long upCategoryId;

    public Category() {
    }

    public Category(String name, long upCategoryId) {
        this.name = name;
        this.upCategoryId = upCategoryId;
    }

    public long getId() {
        return id;
    }

    public Category setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Category setName(String name) {
        this.name = name;
        return this;
    }

    public long getUpCategoryId() {
        return upCategoryId;
    }

    public Category setUpCategoryId(long upCategoryId) {
        this.upCategoryId = upCategoryId;
        return this;
    }

}
