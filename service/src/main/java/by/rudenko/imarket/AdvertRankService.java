package by.rudenko.imarket;

import by.rudenko.imarket.dto.UserDTO;
import by.rudenko.imarket.exception.NoSuchIdException;

import java.util.List;


public interface UserService {
    UserDTO findById(long id) throws NoSuchIdException;

    List<UserDTO> getAllUsersList(int pageNumber, int pageSize);

    boolean addNewUser(UserDTO orderDTO);

    boolean deleteUser(UserDTO orderDTO);

    boolean update(UserDTO orderDTO);
}



