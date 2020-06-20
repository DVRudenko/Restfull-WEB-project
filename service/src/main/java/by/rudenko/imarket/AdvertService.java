package by.rudenko.imarket;

import by.rudenko.imarket.dto.AdvertDTO;
import by.rudenko.imarket.dto.AdvertShortDTO;
import by.rudenko.imarket.exception.NoSuchIdException;
import by.rudenko.imarket.model.Advert;

import java.util.List;


public interface AdvertService {
    AdvertDTO findById(Long id) throws NoSuchIdException;

    List<AdvertShortDTO> getAllAdvertsList(int pageNumber, int pageSize);

    List<AdvertDTO> getFullAdvertsList(int pageNumber, int pageSize);

    AdvertDTO getFullAdvertByID (Long id) throws NoSuchIdException;

    boolean addNewAdvert(AdvertDTO advertDTO);

    boolean deleteAdvert(AdvertDTO advertDTO);

    boolean update(AdvertDTO advertDTO);



    List<AdvertDTO> getAllSortedAdverts(int pageNumber, int pageSize);
}



