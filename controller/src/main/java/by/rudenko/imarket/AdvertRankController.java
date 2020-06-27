package by.rudenko.imarket;

import by.rudenko.imarket.dto.AdvertRankDTO;
import by.rudenko.imarket.exception.NoSuchIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/advertRanks")
public class AdvertRankController<T extends AdvertRankDTO> {

    private final AdvertRankService advertRankService;

    //TODO как добавить зависимость из модуля Launcher без циклической ссылки???
    //private final IMarketConfig iMarketConfig;
    //IMarketConfig.defaultPageSize //количество записей на страницу по умолчанию (из файла свойств)

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

    //тип Get /guests/id - получить сущность по Id
    @GetMapping(value = "/{id}")
    public AdvertRankDTO getAllAdvertRanks(@PathVariable(value = "id") Long id) throws NoSuchIdException {
        return advertRankService.findById(id);
    }

    //тип Post /advertRanks/JSON добавить новую запись
    // TODO посмотреть как отрабатывает возврат ответа
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> addNewAdvertRank (@RequestBody AdvertRankDTO advertRankDTO) {
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
    //TODO как лучше через POST или PUT
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateAdvertRank (@RequestBody AdvertRankDTO advertRankDTO) {
        advertRankService.update(advertRankDTO);
    }

}
