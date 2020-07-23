package by.rudenko.imarket.impl;

import by.rudenko.imarket.ProfileDao;
import by.rudenko.imarket.UserDao;
import by.rudenko.imarket.UserService;
import by.rudenko.imarket.dto.UserDTO;
import by.rudenko.imarket.exception.NoSuchIdException;
import by.rudenko.imarket.model.*;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.metamodel.SingularAttribute;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final ProfileDao profileDao;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userDao, ProfileDao profileDao, ModelMapper modelMapper, BCryptPasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.profileDao = profileDao;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean addNewUser(UserDTO userDTO) {
        final User user = modelMapper.map(userDTO, User.class);
        Profile profile = new Profile(user); //создаем профиль по умолчанию
        user.setProfile(profile);
        //шифруем пароль
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userDao.save(user);

        return true;
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public UserDTO findById(Long id) throws NoSuchIdException {
        final User userEntity = userDao.findByID(id);

        return modelMapper.map(userEntity, UserDTO.class);
    }


    @Override
    public List<UserDTO> getAllUsersList(int pageNumber, int pageSize) {
        List <SingularAttribute> attributes = new ArrayList<>();
        attributes.add(User_.profile);
        return userDao.getAll(pageNumber, pageSize, attributes).stream()
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
        User user = modelMapper.map(userDTO, User.class);
        //шифруем пароль
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return userDao.update(user);
    }

    @Override
    public Long getCount() {
        return userDao.getCount();
    }
}
