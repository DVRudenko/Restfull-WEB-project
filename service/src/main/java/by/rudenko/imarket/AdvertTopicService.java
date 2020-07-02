package by.rudenko.imarket;

import by.rudenko.imarket.dto.AdvertTopicDTO;
import by.rudenko.imarket.exception.NoSuchIdException;

import java.util.List;


public interface AdvertTopicService {
    AdvertTopicDTO findById(Long id) throws NoSuchIdException;

    List<AdvertTopicDTO> getAllAdvertTopicsList(int pageNumber, int pageSize);

    boolean addNewAdvertTopic(AdvertTopicDTO advertTopicDTO);

    boolean deleteAdvertTopic(AdvertTopicDTO advertTopicDTO);

    boolean update(AdvertTopicDTO advertTopicDTO);

    Long entityCount ();
}



