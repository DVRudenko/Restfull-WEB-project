package by.rudenko.imarket;

import by.rudenko.imarket.dto.DebateDTO;
import by.rudenko.imarket.exception.NoSuchIdException;

import java.util.List;


public interface DebateService {
    DebateDTO findById(Long id) throws NoSuchIdException;

    List<DebateDTO> getAllDebatesList(int pageNumber, int pageSize);

    boolean addNewDebate(DebateDTO debateDTO);

    boolean deleteDebate(DebateDTO debateDTO);

    boolean update(DebateDTO debateDTO);
}



