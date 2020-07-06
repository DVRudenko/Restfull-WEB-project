package by.rudenko.imarket.impl;

import by.rudenko.imarket.DebateDao;
import by.rudenko.imarket.DebateService;
import by.rudenko.imarket.dto.DebateDTO;
import by.rudenko.imarket.exception.NoSuchIdException;
import by.rudenko.imarket.model.Debate;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DebateServiceImpl implements DebateService {

    private final DebateDao debateDao;
    private final ModelMapper modelMapper;

    public DebateServiceImpl(DebateDao debateDao, ModelMapper modelMapper) {
        this.debateDao = debateDao;
        this.modelMapper = modelMapper;
    }


    //вывести полный список объявлений с вложениями
    @Override
    public List<DebateDTO> getFullDebatesList(int pageNumber, int pageSize) {

        return debateDao.getFullDebates(pageNumber, pageSize).stream()
                .map(x -> modelMapper.map(x, DebateDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean addNewDebate(DebateDTO debateDTO) {
        // маппинг из ДТО в  Entity
        final Debate debate = modelMapper.map(debateDTO, Debate.class);
        debateDao.save(debate);
        return true;
    }


    @Override
    public DebateDTO findById(Long id) throws NoSuchIdException {
        final Debate debateEntity = debateDao.getFullDebateByID(id);

        return modelMapper.map(debateEntity, DebateDTO.class);
    }

    @Override
    public List<DebateDTO> getAllDebatesList(int pageNumber, int pageSize) {

        return debateDao.getAll(pageNumber, pageSize).stream()
                .map(x -> modelMapper.map(x, DebateDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteDebate(DebateDTO debateDTO) {

        final Debate debate = modelMapper.map(debateDTO, Debate.class);
        debateDao.delete(debate);
        return true;
    }

    @Override
    public boolean update(DebateDTO debateDTO) {
        final Debate debate = modelMapper.map(debateDTO, Debate.class);
        debateDao.update(debate);
        return true;
    }

    @Override
    public Long entityCount() {
        return debateDao.entityCount();
    }
}
