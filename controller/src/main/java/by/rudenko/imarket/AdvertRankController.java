package by.rudenko.imarket;

import by.rudenko.imarket.dto.AdvertRankDTO;
import by.rudenko.imarket.exception.NoSuchIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for advertRanks requests (get, post, delete, etc.)
 *
 * @author Dmitry Rudenko
 * @version 1.0
 */
@RestController
@RequestMapping("/advertRanks")
public class AdvertRankController {

    private final AdvertRankService advertRankService;

    public AdvertRankController(final AdvertRankService advertRankService) {
        this.advertRankService = advertRankService;
    }


    @GetMapping
    public List<AdvertRankDTO> getAllAdvertRanks(
            @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return advertRankService.getAllAdvertRanksList(pageNumber, pageSize);
    }

    @GetMapping(value = "/count")
    public Long getUsersCount() {
        return advertRankService.entityCount();
    }


    @GetMapping(value = "/{id}")
    public AdvertRankDTO getAllAdvertRanks(@PathVariable(value = "id") Long id) throws NoSuchIdException {
        return advertRankService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> addNewAdvertRank(@RequestBody AdvertRankDTO advertRankDTO) {
        advertRankService.addNewAdvertRank(advertRankDTO);
        return ResponseEntity.ok("advertRank saved");
    }


    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAdvertRank(@PathVariable(value = "id") Long id) throws NoSuchIdException {

        advertRankService.deleteAdvertRank(advertRankService.findById(id));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateAdvertRank(@RequestBody AdvertRankDTO advertRankDTO) {
        advertRankService.update(advertRankDTO);
    }

}
