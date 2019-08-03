package de.home.petazwei.subcategory;

import de.home.petazwei.BaseService;

import javax.ejb.Stateless;

@Stateless
public class SubcategoryService extends BaseService<Subcategory> {

    public SubcategoryService() {
        super(Subcategory.class);
    }
}