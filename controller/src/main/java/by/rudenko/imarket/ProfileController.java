package by.rudenko.imarket;

import by.rudenko.imarket.dto.ProfileDTO;
import by.rudenko.imarket.exception.NoSuchIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for profiles requests (get, post, delete, etc.)
 *
 * @author Dmitry Rudenko
 * @version 1.0
 */
@RestController
@RequestMapping("/profiles")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(final ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public List<ProfileDTO> getAllProfiles(
            @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return profileService.getFullProfiles(pageNumber, pageSize);
    }

    @GetMapping(value = "/count")
    public Long getUsersCount() {
        return profileService.entityCount();
    }

    @GetMapping(value = "/{id}")
    public ProfileDTO getAllProfiles(@PathVariable(value = "id") Long id) throws NoSuchIdException {
        return profileService.findById(id);
    }

   @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProfile(@PathVariable(value = "id") Long id) throws NoSuchIdException {

        profileService.deleteProfile(profileService.findById(id));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateProfile(@RequestBody ProfileDTO profileDTO) {
        profileService.update(profileDTO);
    }

}
