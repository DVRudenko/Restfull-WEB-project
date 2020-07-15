package by.rudenko.imarket.impl;

import by.rudenko.imarket.AdvertTopicDao;
import by.rudenko.imarket.AdvertTopicService;
import by.rudenko.imarket.dto.AdvertTopicDTO;
import by.rudenko.imarket.exception.NoSuchIdException;
import by.rudenko.imarket.model.AdvertTopic;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AdvertTopicServiceImpl implements AdvertTopicService {

    private final AdvertTopicDao advertTopicDao;
    private final ModelMapper modelMapper;

    public AdvertTopicServiceImpl(AdvertTopicDao advertTopicDao, ModelMapper modelMapper) {
        this.advertTopicDao = advertTopicDao;
        this.modelMapper = modelMapper;
    }


    @Override
    public boolean addNewAdvertTopic(AdvertTopicDTO advertTopicDTO) {
        // маппинг из ДТО в  Entity
        final AdvertTopic advertTopic = modelMapper.map(advertTopicDTO, AdvertTopic.class);
        advertTopicDao.save(advertTopic);
        return true;
    }


    @Override
    public AdvertTopicDTO findById(Long id) throws NoSuchIdException {
        final AdvertTopic advertTopicEntity = advertTopicDao.findByID(id);

        return modelMapper.map(advertTopicEntity, AdvertTopicDTO.class);
    }

    @Override
    public List<AdvertTopicDTO> getAllAdvertTopicsList(int pageNumber, int pageSize) {

        return advertTopicDao.getAll(pageNumber, pageSize, null).stream()
                .map(x -> modelMapper.map(x, AdvertTopicDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteAdvertTopic(AdvertTopicDTO advertTopicDTO) {

        final AdvertTopic advertTopic = modelMapper.map(advertTopicDTO, AdvertTopic.class);
        advertTopicDao.delete(advertTopic);
        return true;
    }

    @Override
    public boolean update(AdvertTopicDTO advertTopicDTO) {
        final AdvertTopic advertTopic = modelMapper.map(advertTopicDTO, AdvertTopic.class);
        advertTopicDao.update(advertTopic);
        return true;
    }

    @Override
    public Long entityCount() {
        return advertTopicDao.getCount();
    }
}
