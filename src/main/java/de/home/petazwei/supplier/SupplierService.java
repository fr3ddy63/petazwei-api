package de.home.petazwei.supplier;

import de.home.petazwei.BaseService;

import javax.ejb.Stateless;

@Stateless
public class SupplierService extends BaseService<Supplier> {

    public SupplierService() {
        super(Supplier.class);
    }
}