package by.rudenko.imarket;

import by.rudenko.imarket.dto.ProfileDTO;
import by.rudenko.imarket.exception.NoSuchIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(final ProfileService profileService) {
        this.profileService = profileService;
    }

    //тип Get /profiles/ - получить весь список с пагинацией
    @GetMapping
    public List<ProfileDTO> getAllProfiles(
            @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return profileService.getFullProfiles(pageNumber, pageSize);
    }

    //тип Get /guests/count - получить количество строк в таблице (пользователей)
    @GetMapping(value = "/count")
    public Long getUsersCount() {
        return profileService.entityCount();
    }

    //тип Get /guests/id - получить сущность по Id
    @GetMapping(value = "/{id}")
    public ProfileDTO getAllProfiles(@PathVariable(value = "id") Long id) throws NoSuchIdException {
        return profileService.findById(id);
    }

    //тип Post /profiles/JSON добавить новую запись
    // TODO посмотреть как отрабатывает возврат ответа
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> addNewProfile(@RequestBody ProfileDTO profileDTO) {
        profileService.addNewProfile(profileDTO);
        return ResponseEntity.ok("profile saved");
    }


    //тип Delete /rooms/id удалить запись по Id
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProfile(@PathVariable(value = "id") Long id) throws NoSuchIdException {

        profileService.deleteProfile(profileService.findById(id));
    }

    //тип Put /profiles/JSON обновить запись
    //TODO как лучше через POST или PUT
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateProfile(@RequestBody ProfileDTO profileDTO) {
        profileService.update(profileDTO);
    }

}
