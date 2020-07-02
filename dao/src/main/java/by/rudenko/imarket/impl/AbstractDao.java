package by.rudenko.imarket.impl;

import by.rudenko.imarket.GenericDao;
import by.rudenko.imarket.exception.NoSuchIdException;
import by.rudenko.imarket.model.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class AbstractDao <T extends Entity, PK extends Number> implements GenericDao<T , PK> {

    private static final Logger LOGGER = LogManager.getLogger("imarketAbstract");

    @PersistenceContext
    protected EntityManager em;

    private Class<T> entityClass;

    public AbstractDao(Class<T> entityClass) {

        this.entityClass = entityClass;
    }


    @Override
    public T findByID(final PK id) throws NoSuchIdException {
        LOGGER.info("Find by ID " + entityClass.getSimpleName());
        T entity = em.find(entityClass, id);
        if (entity != null) {
            return entity;
        } else {
            LOGGER.error("No such ID" + id,  entityClass.getSimpleName());
            throw new NoSuchIdException("No such ID" + id);

        }
    }

    //поиск количества элементов в классе (в таблице)
    @Override
    public Long entityCount() {
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        cq.select(cb.count(cq.from(entityClass)));
        //если вдруг понадобиться по отдельному условию
        //cq.where(/*something stuff*/);
        return em.createQuery(cq).getSingleResult();
    }



    //запрос с пагинацией
    @Override
    public List<T> getAll(int pageNumber, int pageSize) {
        LOGGER.info("Find all by " + entityClass.getSimpleName());
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<T> cq = cb.createQuery(entityClass);
        final Root<T> root = cq.from(entityClass);
        CriteriaQuery<T> select = cq.select(root);

        TypedQuery<T> typedQuery = em.createQuery(select);
        typedQuery.setFirstResult((pageNumber-1) * pageSize);
        typedQuery.setMaxResults(pageSize);
        return typedQuery.getResultList();
    }


    @Override
     public boolean save(final T entity) {
        em.persist(em.contains (entity) ? entity : em.merge(entity));
        em.persist(entity);
        LOGGER.info("1 row added to DB. Add new " + entityClass.getSimpleName());
        return true;
    }

    @Override
 
    public boolean delete(final T deleteEntity) {
        em.remove(em.contains(deleteEntity) ? deleteEntity : em.merge(deleteEntity));
        LOGGER.info("1 row remove from DB. by " + entityClass.getSimpleName());
        return true;
    }


    @Override
    public boolean update(final T updateEntity) {
        em.refresh(em.contains(updateEntity) ? updateEntity : em.merge(updateEntity));
        LOGGER.info("1 row updated to DB. Add new " + entityClass.getSimpleName());
        return true;
    }
}
