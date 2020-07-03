package by.rudenko.imarket;

import by.rudenko.imarket.exception.NoSuchIdException;
import by.rudenko.imarket.model.Profile;

import java.util.List;


public interface ProfileDao extends GenericDao<Profile, Long> {


    //получаем все поля Profile с ленивой инициализацией
    //используем пагинацию
    List<Profile> getFullProfiles(int pageNumber, int pageSize);

    Profile getFullProfileByID(Long id) throws NoSuchIdException;
}

