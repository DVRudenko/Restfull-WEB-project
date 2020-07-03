package by.rudenko.imarket;

import by.rudenko.imarket.exception.NoSuchIdException;
import by.rudenko.imarket.model.Advert;

import java.util.List;


public interface AdvertDao extends GenericDao<Advert, Long> {

    //получить все объявления с вложениями
    List<Advert> getFullAdverts(int pageNumber, int pageSize);

    //получить объявление с вложениями по Id
    Advert getFullAdvertByID(Long id) throws NoSuchIdException;

    List<Advert> getSortedAdverts(int pageNumber, int pageSize);

    // сортируем объявления по рангу (VIP,Prior,Usual)
    // с учетом темы
    //с пагинацией
    List<Advert> getSortedAdvertsByTopic(String topic, int pageNumber, int pageSize);


}

