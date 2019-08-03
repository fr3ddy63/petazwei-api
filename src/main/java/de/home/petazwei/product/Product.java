package de.home.petazwei.product;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import de.home.petazwei.subcategory.Subcategory;
import de.home.petazwei.supplier.Supplier;

@Entity
@Table(name = "product")
public class Product {

    @Id
    private Integer id;
    
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    private String name;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    private String manufacturer;

    /**
     * @return the manufacturer
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * @param manufacturer the manufacturer to set
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @ManyToOne(optional = false)
    private Subcategory subcategory;

    /**
     * @return the subcategory
     */
    public Subcategory getSubcategory() {
        return subcategory;
    }

    /**
     * @param subcategory the subcategory to set
     */
    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
        name = "association",
        joinColumns = { @JoinColumn(name = "product_id") },
        inverseJoinColumns = { @JoinColumn(name = "supplier_id") }
    )
    private Set<Supplier> suppliers = new HashSet<>();

    public Set<Supplier> getSuppliers() {
        return suppliers;
    }

    protected Product() {}
}