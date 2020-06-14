package by.rudenko.imarket;

import by.rudenko.imarket.dto.ProfileDTO;
import by.rudenko.imarket.exception.NoSuchIdException;
import by.rudenko.imarket.model.Profile;

import java.util.List;


public interface ProfileService {
    ProfileDTO findById(Long id) throws NoSuchIdException;

    List<ProfileDTO> getAllProfilesList(int pageNumber, int pageSize);

    List<ProfileDTO> getFullProfiles(int pageNumber, int pageSize);

    boolean addNewProfile(ProfileDTO profileDTO);

    boolean deleteProfile(ProfileDTO profileDTO);

    boolean update(ProfileDTO profileDTO);
}



