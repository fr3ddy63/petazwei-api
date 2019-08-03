package de.home.petazwei.category;

import de.home.petazwei.BaseService;

import javax.ejb.Stateless;

@Stateless
public class CategoryService extends BaseService<Category> {

    public CategoryService() {
        super(Category.class);
    }
}