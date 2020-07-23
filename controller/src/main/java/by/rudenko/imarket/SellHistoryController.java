package by.rudenko.imarket;

import by.rudenko.imarket.dto.SellHistoryDTO;
import by.rudenko.imarket.exception.NoSuchIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for sellHistories requests (get, post, delete, etc.)
 *
 * @author Dmitry Rudenko
 * @version 1.0
 */
@RestController
@RequestMapping("/sellHistories")
public class SellHistoryController {

    private final SellHistoryService sellHistoryService;

    public SellHistoryController(final SellHistoryService sellHistoryService) {
        this.sellHistoryService = sellHistoryService;
    }

    @GetMapping
    public List<SellHistoryDTO> getAllSellHistories(
            @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return sellHistoryService.getFullSellHistoriesList(pageNumber, pageSize);
    }

    @GetMapping(value = "/{id}")
    public SellHistoryDTO getAllSellHistories(@PathVariable(value = "id") Long id) throws NoSuchIdException {
        return sellHistoryService.findById(id);
    }

    @GetMapping(value = "/count")
    public Long getUsersCount() {
        return sellHistoryService.entityCount();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> addNewSellHistory(@RequestBody SellHistoryDTO sellHistoryDTO) {
        sellHistoryService.addNewSellHistory(sellHistoryDTO);
        return ResponseEntity.ok("sellHistory saved");
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSellHistory(@PathVariable(value = "id") Long id) throws NoSuchIdException {

        sellHistoryService.deleteSellHistory(sellHistoryService.findById(id));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateSellHistory(@RequestBody SellHistoryDTO sellHistoryDTO) {
        sellHistoryService.update(sellHistoryDTO);
    }

}
