package by.rudenko.imarket;

import by.rudenko.imarket.dto.AdvertDTO;
import by.rudenko.imarket.dto.AdvertShortDTO;
import by.rudenko.imarket.exception.NoSuchIdException;

import java.util.List;


public interface AdvertService {
    AdvertDTO findById(Long id) throws NoSuchIdException;


    List<AdvertDTO> getFullAdvertsList(int pageNumber, int pageSize);

    AdvertDTO getFullAdvertByID(Long id) throws NoSuchIdException;

    boolean addNewAdvert(AdvertDTO advertDTO);

    boolean deleteAdvert(AdvertDTO advertDTO);

    boolean update(AdvertDTO advertDTO);

    List<AdvertShortDTO> getAllSortedAdverts(int pageNumber, int pageSize);

    List<AdvertShortDTO> getAllSortedAdvertsByTopic(String topic, int pageNumber, int pageSize);

    List<AdvertShortDTO> getAllShortAdverts(int pageNumber, int pageSize);

    Long entityCount();
}



