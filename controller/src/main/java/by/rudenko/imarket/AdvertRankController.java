package by.rudenko.imarket;

import by.rudenko.imarket.dto.AdvertRankDTO;
import by.rudenko.imarket.exception.NoSuchIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/advertRanks")
public class AdvertRankController {

    private final AdvertRankService advertRankService;

    public AdvertRankController(final AdvertRankService advertRankService) {
        this.advertRankService = advertRankService;
    }


    //тип Get /advertRanks/ - получить весь список с пагинацией
    @GetMapping
    public List<AdvertRankDTO> getAllAdvertRanks(
            @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return advertRankService.getAllAdvertRanksList(pageNumber, pageSize);
    }

    //тип Get /count - получить количество строк в таблице
    @GetMapping(value = "/count")
    public Long getUsersCount() {
        return advertRankService.entityCount();
    }


    //тип Get /guests/id - получить сущность по Id
    @GetMapping(value = "/{id}")
    public AdvertRankDTO getAllAdvertRanks(@PathVariable(value = "id") Long id) throws NoSuchIdException {
        return advertRankService.findById(id);
    }

    //тип Post /advertRanks/JSON добавить новую запись
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> addNewAdvertRank(@RequestBody AdvertRankDTO advertRankDTO) {
        advertRankService.addNewAdvertRank(advertRankDTO);
        return ResponseEntity.ok("advertRank saved");
    }


    //тип Delete /rooms/id удалить запись по Id
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAdvertRank(@PathVariable(value = "id") Long id) throws NoSuchIdException {

        advertRankService.deleteAdvertRank(advertRankService.findById(id));
    }

    //тип Put /advertRanks/JSON обновить запись
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateAdvertRank(@RequestBody AdvertRankDTO advertRankDTO) {
        advertRankService.update(advertRankDTO);
    }

}
