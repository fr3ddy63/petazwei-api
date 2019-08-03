package de.home.petazwei;

import de.home.petazwei.product.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public class BaseService<E> {

    @PersistenceContext
    protected EntityManager entityManager;

    protected final Class<E> entityClass;

    protected BaseService(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    public Optional<E> find(Integer id) {
        return Optional.ofNullable(this.entityManager.find(this.entityClass, id));
    }

    public List<E> find(Parameter param) {
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<E> cq = cb.createQuery(this.entityClass);
        Root<E> qr = cq.from(this.entityClass);
        return this.entityManager.createQuery(cq)
                .setFirstResult(param.getFirstResult())
                .setMaxResults(param.getRpp())
                .getResultList();
    }

    public Long count() {
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        cq.select(cb.count(cq.from(entityClass)));
        return this.entityManager.createQuery(cq).getSingleResult();
    }
}
