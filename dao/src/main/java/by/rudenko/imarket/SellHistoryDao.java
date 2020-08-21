package by.rudenko.imarket;

import by.rudenko.imarket.exception.NoSuchIdException;
import by.rudenko.imarket.model.SellHistory;

import java.util.List;


public interface SellHistoryDao extends GenericDao<SellHistory, Long> {


    // список всех SellHistory без сортировки
    List<SellHistory> getFullSellHistories(int pageNumber, int pageSize);

    //получаем все поля SellHistory по ID с ленивой инициализацией
    SellHistory getFullSellHistoryByID(Long id) throws NoSuchIdException;
}

