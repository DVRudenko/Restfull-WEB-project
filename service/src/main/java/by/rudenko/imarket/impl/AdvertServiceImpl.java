package by.rudenko.imarket.impl;

import by.rudenko.imarket.AdvertDao;
import by.rudenko.imarket.AdvertService;
import by.rudenko.imarket.dto.AdvertDTO;
import by.rudenko.imarket.dto.AdvertShortDTO;
import by.rudenko.imarket.exception.NoSuchIdException;
import by.rudenko.imarket.model.Advert;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AdvertServiceImpl implements AdvertService {

    private final AdvertDao advertDao;
    private final ModelMapper modelMapper;

    public AdvertServiceImpl(AdvertDao advertDao, ModelMapper modelMapper) {
        this.advertDao = advertDao;
        this.modelMapper = modelMapper;
    }


    @Override
    public boolean addNewAdvert(AdvertShortDTO advertShortDTO) {
        Advert advert = modelMapper.map(advertShortDTO, Advert.class);
        advertDao.save(advert);
        return true;
    }


    @Override
    public AdvertDTO findById(Long id) throws NoSuchIdException {
        final Advert advertEntity = advertDao.findByID(id);

        return modelMapper.map(advertEntity, AdvertDTO.class);
    }

    //вывести сокращенный вариант объявлений
    @Override
    public List<AdvertShortDTO> getAllShortAdverts(int pageNumber, int pageSize) {

        return advertDao.getAll(pageNumber, pageSize, null).stream()
                .map(x -> modelMapper.map(x, AdvertShortDTO.class))
                .collect(Collectors.toList());
    }

    //вывести полный список объявлений с вложениями
    @Override
    public List<AdvertDTO> getFullAdvertsList(int pageNumber, int pageSize) {

        return advertDao.getFullAdverts(pageNumber, pageSize).stream()
                .map(x -> modelMapper.map(x, AdvertDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public AdvertDTO getFullAdvertByID(Long id) throws NoSuchIdException {
        final Advert advertEntity = advertDao.getFullAdvertByID(id);

        return modelMapper.map(advertEntity, AdvertDTO.class);
    }

    @Override
    public boolean deleteAdvert(AdvertDTO advertDTO) {

        final Advert advert = modelMapper.map(advertDTO, Advert.class);
        advertDao.delete(advert);
        return true;
    }

    @Override
    public boolean update(AdvertShortDTO advertShortDTO) {
        final Advert advert = modelMapper.map(advertShortDTO, Advert.class);
        advertDao.update(advert);
        return true;
    }

    @Override
    public List<AdvertShortDTO> getAllSortedAdverts(int pageNumber, int pageSize) {
        return advertDao.getSortedAdverts(pageNumber, pageSize).stream()
                .map(x -> modelMapper.map(x, AdvertShortDTO.class))
                .collect(Collectors.toList());

    }

    @Override
    public List<AdvertShortDTO> getAllSortedAdvertsByTopic(String topic, int pageNumber, int pageSize) {
        return advertDao.getSortedAdvertsByTopic(topic, pageNumber, pageSize).stream()
                .map(x -> modelMapper.map(x, AdvertShortDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Long entityCount() {
        return advertDao.getCount();
    }

    @Override
    public List<AdvertShortDTO> getAllSortedAdvertsByUserRank(int pageNumber, int pageSize) {
        return advertDao.getSortedAdvertsByUserRank(pageNumber, pageSize).stream()
                .map(x -> modelMapper.map(x, AdvertShortDTO.class))
                .collect(Collectors.toList());
    }
}
