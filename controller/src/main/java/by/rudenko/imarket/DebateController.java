package by.rudenko.imarket;

import by.rudenko.imarket.dto.DebateDTO;
import by.rudenko.imarket.exception.NoSuchIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for debates requests (get, post, delete, etc.)
 *
 * @author Dmitry Rudenko
 * @version 1.0
 */
@RestController
@RequestMapping("/debates")
public class DebateController {

    private final DebateService debateService;

    public DebateController(final DebateService debateService) {
        this.debateService = debateService;
    }

    @GetMapping
    public List<DebateDTO> getAllDebates(
            @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return debateService.getFullDebatesList(pageNumber, pageSize);
    }

    @GetMapping(value = "/count")
    public Long getUsersCount() {
        return debateService.entityCount();
    }

    @GetMapping(value = "/{id}")
    public DebateDTO getAllDebates(@PathVariable(value = "id") Long id) throws NoSuchIdException {
        return debateService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> addNewDebate(@RequestBody DebateDTO debateDTO) {
        debateService.addNewDebate(debateDTO);
        return ResponseEntity.ok("debate saved");
    }


    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteDebate(@PathVariable(value = "id") Long id) throws NoSuchIdException {

        debateService.deleteDebate(debateService.findById(id));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateDebate(@RequestBody DebateDTO debateDTO) {
        debateService.update(debateDTO);
    }

}
