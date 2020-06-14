package by.rudenko.imarket;

import by.rudenko.imarket.dto.AdvertRankDTO;
import by.rudenko.imarket.exception.NoSuchIdException;

import java.util.List;


public interface AdvertRankService {
    AdvertRankDTO findById(Long id) throws NoSuchIdException;

    List<AdvertRankDTO> getAllAdvertRanksList(int pageNumber, int pageSize);

    boolean addNewAdvertRank(AdvertRankDTO advertRankDTO);

    boolean deleteAdvertRank(AdvertRankDTO advertRankDTO);

    boolean update(AdvertRankDTO advertRankDTO);
}



