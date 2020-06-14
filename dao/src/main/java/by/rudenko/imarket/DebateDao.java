package by.rudenko.imarket;


import by.rudenko.imarket.exception.NoSuchIdException;
import by.rudenko.imarket.model.Debate;

import java.util.List;

public interface DebateDao extends GenericDao <Debate, Long> {


    // список всех Debate без сортировки
    List<Debate> getFullDebates(int pageNumber, int pageSize);

    //получаем все поля Debate по ID с ленивой инициализацией
    Debate getFullDebateByID(Long id) throws NoSuchIdException;
}

