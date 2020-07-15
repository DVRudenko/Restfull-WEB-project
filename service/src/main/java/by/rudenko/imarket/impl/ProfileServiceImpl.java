package by.rudenko.imarket.impl;

import by.rudenko.imarket.ProfileDao;
import by.rudenko.imarket.ProfileService;
import by.rudenko.imarket.dto.ProfileDTO;
import by.rudenko.imarket.exception.NoSuchIdException;
import by.rudenko.imarket.model.Profile;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {

    private final ProfileDao profileDao;
    private final ModelMapper modelMapper;

    public ProfileServiceImpl(ProfileDao profileDao, ModelMapper modelMapper) {
        this.profileDao = profileDao;
        this.modelMapper = modelMapper;
    }

    /*//Профиль будет создаваться автоматом (по умолчанию) при создании нового юзера
    @Override
    public boolean addNewProfile(ProfileDTO profileDTO) {
        // маппинг из ДТО в  Entity
        final Profile profile = modelMapper.map(profileDTO, Profile.class);
        profileDao.save(profile);
        return true;
    }*/


    @Override
    public ProfileDTO findById(Long id) throws NoSuchIdException {
        final Profile profileEntity = profileDao.getFullProfileByID(id);

        return modelMapper.map(profileEntity, ProfileDTO.class);
    }

    @Override
    public List<ProfileDTO> getAllProfilesList(int pageNumber, int pageSize) {

        return profileDao.getAll(pageNumber, pageSize, null).stream()
                .map(x -> modelMapper.map(x, ProfileDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProfileDTO> getFullProfiles(int pageNumber, int pageSize) {
        return profileDao.getFullProfiles(pageNumber, pageSize).stream()
                .map(x -> modelMapper.map(x, ProfileDTO.class))
                .collect(Collectors.toList());
    }


    @Override
    public boolean deleteProfile(ProfileDTO profileDTO) {

        final Profile profile = modelMapper.map(profileDTO, Profile.class);
        profileDao.delete(profile);
        return true;
    }

    @Override
    public boolean update(ProfileDTO profileDTO) {
        final Profile profile = modelMapper.map(profileDTO, Profile.class);
        profileDao.update(profile);
        return true;
    }

    @Override
    public Long entityCount() {
        return profileDao.getCount();
    }
}
