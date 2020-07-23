package by.rudenko.imarket.impl;

import by.rudenko.imarket.AdvertRankDao;
import by.rudenko.imarket.AdvertRankService;
import by.rudenko.imarket.dto.AdvertRankDTO;
import by.rudenko.imarket.exception.NoSuchIdException;
import by.rudenko.imarket.model.AdvertRank;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AdvertRankServiceImpl implements AdvertRankService {

    private final AdvertRankDao advertRankDao;
    private final ModelMapper modelMapper;

    public AdvertRankServiceImpl(AdvertRankDao advertRankDao, ModelMapper modelMapper) {
        this.advertRankDao = advertRankDao;
        this.modelMapper = modelMapper;
    }


    @Override
    public boolean addNewAdvertRank(AdvertRankDTO advertRankDTO) {
        final AdvertRank advertRank = modelMapper.map(advertRankDTO, AdvertRank.class);
        advertRankDao.save(advertRank);
        return true;
    }


    @Override
    public AdvertRankDTO findById(Long id) throws NoSuchIdException {
        final AdvertRank advertRankEntity = advertRankDao.findByID(id);

        return modelMapper.map(advertRankEntity, AdvertRankDTO.class);
    }

    @Override
    public List<AdvertRankDTO> getAllAdvertRanksList(int pageNumber, int pageSize) {

        return advertRankDao.getAll(pageNumber, pageSize, null).stream()
                .map(x -> modelMapper.map(x, AdvertRankDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteAdvertRank(AdvertRankDTO advertRankDTO) {

        final AdvertRank advertRank = modelMapper.map(advertRankDTO, AdvertRank.class);
        advertRankDao.delete(advertRank);
        return true;
    }

    @Override
    public boolean update(AdvertRankDTO advertRankDTO) {
        final AdvertRank advertRank = modelMapper.map(advertRankDTO, AdvertRank.class);
        advertRankDao.update(advertRank);
        return true;
    }

    @Override
    public Long entityCount() {
        return advertRankDao.getCount();
    }
}
