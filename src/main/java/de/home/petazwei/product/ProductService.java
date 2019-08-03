package de.home.petazwei.product;

import de.home.petazwei.BaseService;

import javax.ejb.Stateless;

@Stateless
public class ProductService extends BaseService<Product> {

/*
    @PersistenceContext
    private EntityManager entityManager;
*/

    public ProductService() {
        super(Product.class);
    }

    /*public List<Product> find() {
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> qr = cq.from(Product.class);
        return this.entityManager.createQuery(cq).getResultList();
    }

    public List<Product> find(Parameter param) {
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> qr = cq.from(Product.class);
        return this.entityManager.createQuery(cq)
                .setFirstResult(param.getFirstResult())
                .setMaxResults(param.getRpp())
                .getResultList();
    }

    public Optional<Product> find(Integer id) {
        return Optional.ofNullable((this.entityManager.find(Product.class, id)));
    }*/
}