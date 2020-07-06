package by.rudenko.imarket.impl;

import by.rudenko.imarket.SellHistoryDao;
import by.rudenko.imarket.SellHistoryService;
import by.rudenko.imarket.dto.SellHistoryDTO;
import by.rudenko.imarket.exception.NoSuchIdException;
import by.rudenko.imarket.model.SellHistory;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SellHistoryServiceImpl implements SellHistoryService {

    private final SellHistoryDao sellHistoryDao;
    private final ModelMapper modelMapper;

    public SellHistoryServiceImpl(SellHistoryDao sellHistoryDao, ModelMapper modelMapper) {
        this.sellHistoryDao = sellHistoryDao;
        this.modelMapper = modelMapper;
    }


    @Override
    public boolean addNewSellHistory(SellHistoryDTO sellHistoryDTO) {
        // маппинг из ДТО в  Entity
        final SellHistory sellHistory = modelMapper.map(sellHistoryDTO, SellHistory.class);
        sellHistoryDao.save(sellHistory);
        return true;
    }


    //вывести полный список историй продаж с вложениями
    @Override
    public List<SellHistoryDTO> getFullSellHistoriesList(int pageNumber, int pageSize) {

        return sellHistoryDao.getFullSellHistories(pageNumber, pageSize).stream()
                .map(x -> modelMapper.map(x, SellHistoryDTO.class))
                .collect(Collectors.toList());
    }


    //вывести полную историю продаж с вложениями по Id
    @Override
    public SellHistoryDTO findById(Long id) throws NoSuchIdException {
        final SellHistory sellHistoryEntity = sellHistoryDao.getFullSellHistoryByID(id);

        return modelMapper.map(sellHistoryEntity, SellHistoryDTO.class);
    }

    @Override
    public boolean deleteSellHistory(SellHistoryDTO sellHistoryDTO) {

        final SellHistory sellHistory = modelMapper.map(sellHistoryDTO, SellHistory.class);
        sellHistoryDao.delete(sellHistory);
        return true;
    }

    @Override
    public boolean update(SellHistoryDTO sellHistoryDTO) {
        final SellHistory sellHistory = modelMapper.map(sellHistoryDTO, SellHistory.class);
        sellHistoryDao.update(sellHistory);
        return true;
    }

    @Override
    public Long entityCount() {
        return sellHistoryDao.entityCount();
    }
}
