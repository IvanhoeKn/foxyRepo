package ru.foxyrepo.website.dao;

import ru.foxyrepo.website.model.Category;

public class CategoryDao extends DAO<Category> {
    public CategoryDao() {
        super();
        setModelClass(Category.class);
    }
}
