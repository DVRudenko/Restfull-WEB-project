package by.rudenko.imarket;

import by.rudenko.imarket.dto.SellHistoryDTO;
import by.rudenko.imarket.exception.NoSuchIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sellHistories")
public class SellHistoryController {

    private final SellHistoryService sellHistoryService;

    public SellHistoryController(final SellHistoryService sellHistoryService) {
        this.sellHistoryService = sellHistoryService;
    }

    //тип Get /sellHistories/ - получить весь список с пагинацией
    @GetMapping
    public List<SellHistoryDTO> getAllSellHistories(
            @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return sellHistoryService.getFullSellHistoriesList(pageNumber, pageSize);
    }

    //тип Get /guests/id - получить сущность по Id
    @GetMapping(value = "/{id}")
    public SellHistoryDTO getAllSellHistories(@PathVariable(value = "id") Long id) throws NoSuchIdException {
        return sellHistoryService.findById(id);
    }

    //тип Get /guests/count - получить количество строк в таблице (пользователей)
    @GetMapping(value = "/count")
    public Long getUsersCount() {
        return sellHistoryService.entityCount();
    }

    //тип Post /sellHistorys/JSON добавить новую запись
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> addNewSellHistory(@RequestBody SellHistoryDTO sellHistoryDTO) {
        sellHistoryService.addNewSellHistory(sellHistoryDTO);
        return ResponseEntity.ok("sellHistory saved");
    }

    //тип Delete /rooms/id удалить запись по Id
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSellHistory(@PathVariable(value = "id") Long id) throws NoSuchIdException {

        sellHistoryService.deleteSellHistory(sellHistoryService.findById(id));
    }

    //тип Put /sellHistorys/JSON обновить запись
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateSellHistory(@RequestBody SellHistoryDTO sellHistoryDTO) {
        sellHistoryService.update(sellHistoryDTO);
    }

}
