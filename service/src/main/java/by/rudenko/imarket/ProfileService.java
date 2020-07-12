package by.rudenko.imarket;

import by.rudenko.imarket.dto.ProfileDTO;
import by.rudenko.imarket.exception.NoSuchIdException;

import java.util.List;


public interface ProfileService {
    ProfileDTO findById(Long id) throws NoSuchIdException;

    List<ProfileDTO> getAllProfilesList(int pageNumber, int pageSize);

    List<ProfileDTO> getFullProfiles(int pageNumber, int pageSize);

    //Профиль будет создаваться автоматом (по умолчанию) при создании нового юзера
    //boolean addNewProfile(ProfileDTO profileDTO);

    boolean deleteProfile(ProfileDTO profileDTO);

    boolean update(ProfileDTO profileDTO);

    Long entityCount();
}



