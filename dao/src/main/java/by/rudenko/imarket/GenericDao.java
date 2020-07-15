package by.rudenko.imarket;

import by.rudenko.imarket.exception.NoSuchIdException;
import by.rudenko.imarket.model.Entity;

import javax.persistence.metamodel.SingularAttribute;
import java.util.List;

public interface GenericDao<T extends Entity, PK extends Number> {

    T findByID(PK id) throws NoSuchIdException;


    //запрос с пагинацией и fetch вложенных полей
    List<T> getAll(int pageNumber, int pageSize, List<SingularAttribute> fetchAttributes);

    boolean save(T entity);

    boolean delete(T entity);

    boolean update(T updateEntity);

    //подсчитываем количество строк в таблице
    Long getCount();

}
