package by.rudenko.imarket.impl;

import by.rudenko.imarket.RoomDao;
import by.rudenko.imarket.dto.RoomDTO;
import by.rudenko.imarket.exception.NoSuchIdException;
import by.rudenko.imarket.model.Room;

import by.rudenko.imarket.RoomService;
import by.rudenko.imarket.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {

    @Autowired
    private  final RoomDao roomDao;
    private  final ModelMapper modelMapper;

    public RoomServiceImpl(RoomDao roomDao, ModelMapper modelMapper) {
        this.roomDao = roomDao;
        this.modelMapper = modelMapper;
    }


    @Override
    public boolean addNewRoom(RoomDTO roomDTO) {
        // маппинг из ДТО в  Entity
        final Room room = modelMapper.map(roomDTO, Room.class);
        roomDao.save(room);
        return true;
    }


    @Override
    public RoomDTO findById(long id) throws NoSuchIdException {
        final Room roomEntity = roomDao.findByID(id);

        return modelMapper.map (roomEntity, RoomDTO.class);
    }



    @Override
    public List<RoomDTO> getAllRooms(int pageNumber, int pageSize) {

        return roomDao.getAll(pageNumber, pageSize).stream()
                .map(x -> modelMapper.map(x, RoomDTO.class))
                .collect(Collectors.toList());        
 
    }

    @Override
    public List<RoomDTO> getAllRoomsByStatus(Utils.RoomStatus status) {

        return roomDao.getAllRoomsByStatus(status).stream()
                .map(x -> modelMapper.map(x, RoomDTO.class))
                .collect(Collectors.toList());
    }

    public int getRoomsNumberByStatus(Utils.RoomStatus status) {

        return roomDao.getRoomsNumberByStatus(status);
    }


    @Override
    public boolean deleteRoom(RoomDTO roomDTO) {
        // маппинг из ДТО в Entity
        final Room room = modelMapper.map(roomDTO, Room.class);
        roomDao.delete(room);
        return true;
    }

    @Override
    public List<RoomDTO> getFreeRoomsSortedByPrice() {
        return roomDao.getFreeRoomsSortedByPrice().stream()
                .map(x -> modelMapper.map(x, RoomDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomDTO> getFreeRoomsSortedByBed() {
        return roomDao.getFreeRoomsSortedByBed().stream()
                .map(x -> modelMapper.map(x, RoomDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomDTO> getFreeRoomsSortedByStars() {
        return roomDao.getFreeRoomsSortedByStar().stream()
                .map(x -> modelMapper.map(x, RoomDTO.class))
                .collect(Collectors.toList());
    }

}
