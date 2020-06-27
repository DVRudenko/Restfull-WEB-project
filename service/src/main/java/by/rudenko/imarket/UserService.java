package by.rudenko.imarket;

import by.rudenko.imarket.dto.UserDTO;
import by.rudenko.imarket.exception.NoSuchIdException;
import by.rudenko.imarket.model.User;

import java.util.List;


public interface UserService {

    // из Сулейманов но переделан в DTO
    User register(User user);

    User findByUsername(String username);

    UserDTO findById(Long id) throws NoSuchIdException;

    List<UserDTO> getAllUsersList(int pageNumber, int pageSize);

    boolean addNewUser(UserDTO userDTO);

    boolean deleteUser(UserDTO userDTO);

    boolean update(UserDTO userDTO);
}



