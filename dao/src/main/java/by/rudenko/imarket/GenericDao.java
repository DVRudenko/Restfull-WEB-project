package by.rudenko.imarket;

import by.rudenko.imarket.exception.NoSuchIdException;
import by.rudenko.imarket.model.Entity;

import java.util.List;

public interface GenericDao<T extends Entity, PK extends Number> {

    T findByID(PK id) throws NoSuchIdException;

    List<T> getAll(int pageNumber, int pageSize);

    boolean save(T entity);

    boolean delete(T entity);

    boolean update(T updateEntity);

}
