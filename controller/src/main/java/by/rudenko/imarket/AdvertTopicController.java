package by.rudenko.imarket;

import by.rudenko.imarket.dto.AdvertTopicDTO;
import by.rudenko.imarket.exception.NoSuchIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for advertTopics requests (get, post, delete, etc.)
 *
 * @author Dmitry Rudenko
 * @version 1.0
 */
@RestController
@RequestMapping("/advertTopics")
public class AdvertTopicController {

    private final AdvertTopicService advertTopicService;

    public AdvertTopicController(final AdvertTopicService advertTopicService) {
        this.advertTopicService = advertTopicService;
    }


    @GetMapping
    public List<AdvertTopicDTO> getAllAdvertTopics(
            @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return advertTopicService.getAllAdvertTopicsList(pageNumber, pageSize);
    }

    @GetMapping(value = "/count")
    public Long getUsersCount() {
        return advertTopicService.entityCount();
    }

    @GetMapping(value = "/{id}")
    public AdvertTopicDTO getAllAdvertTopics(@PathVariable(value = "id") Long id) throws NoSuchIdException {
        return advertTopicService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> addNewAdvertTopic(@RequestBody AdvertTopicDTO advertTopicDTO) {
        advertTopicService.addNewAdvertTopic(advertTopicDTO);
        return ResponseEntity.ok("advertTopic saved");
    }


    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAdvertTopic(@PathVariable(value = "id") Long id) throws NoSuchIdException {

        advertTopicService.deleteAdvertTopic(advertTopicService.findById(id));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateAdvertTopic(@RequestBody AdvertTopicDTO advertTopicDTO) {
        advertTopicService.update(advertTopicDTO);
    }

}
