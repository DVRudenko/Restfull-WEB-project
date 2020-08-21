package by.rudenko.imarket;

import by.rudenko.imarket.dto.SellHistoryDTO;
import by.rudenko.imarket.exception.NoSuchIdException;

import java.util.List;


public interface SellHistoryService {

    List<SellHistoryDTO> getFullSellHistoriesList(int pageNumber, int pageSize);

    SellHistoryDTO findById(Long id) throws NoSuchIdException;


    boolean addNewSellHistory(SellHistoryDTO sellHistoryDTO);

    boolean deleteSellHistory(SellHistoryDTO sellHistoryDTO);

    boolean update(SellHistoryDTO sellHistoryDTO);

    Long entityCount();
}



