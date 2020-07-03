package by.rudenko.imarket.impl;

import by.rudenko.imarket.UserDao;
import by.rudenko.imarket.UserService;
import by.rudenko.imarket.dto.UserDTO;
import by.rudenko.imarket.exception.NoSuchIdException;
import by.rudenko.imarket.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userDao, ModelMapper modelMapper, BCryptPasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean addNewUser(UserDTO userDTO) {
        final User user = modelMapper.map(userDTO, User.class);
        userDao.save(user);
        return true;
    }

    @Override
    public User findByUsername(String username) {
        User result = userDao.findByUsername(username);
        //log.info("IN findByUsername - user: {} found by username: {}", result, username);
        return result;
    }


    @Override
    public UserDTO findById(Long id) throws NoSuchIdException {
        final User userEntity = userDao.findByID(id);

        return modelMapper.map(userEntity, UserDTO.class);
    }


    @Override
    public List<UserDTO> getAllUsersList(int pageNumber, int pageSize) {

        return userDao.getAll(pageNumber, pageSize).stream()
                .map(x -> modelMapper.map(x, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteUser(UserDTO userDTO) {
        final User user = modelMapper.map(userDTO, User.class);
        return userDao.delete(user);
    }

    @Override
    public boolean update(UserDTO userDTO) {
        final User user = modelMapper.map(userDTO, User.class);
        return userDao.update(user);
    }

    @Override
    public Long entityCount() {
        return userDao.entityCount();
    }
}
