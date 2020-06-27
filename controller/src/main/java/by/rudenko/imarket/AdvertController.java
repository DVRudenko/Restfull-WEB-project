package by.rudenko.imarket;

import by.rudenko.imarket.dto.AdvertDTO;
import by.rudenko.imarket.exception.NoSuchIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adverts")
public class AdvertController<T extends AdvertDTO> {

    private final AdvertService advertService;
    
    //TODO как добавить зависимость из модуля Launcher без циклической ссылки???
    //private final IMarketConfig iMarketConfig;
    //IMarketConfig.defaultPageSize //количество записей на страницу по умолчанию (из файла свойств)

    public AdvertController(final AdvertService advertService) {
        this.advertService = advertService;
    }


    //тип Get /adverts/ - получить весь список объявлений с пагинацией (без сортировки)
    @GetMapping
    public List<AdvertDTO> getAllAdverts(
            @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return advertService.getFullAdvertsList(pageNumber, pageSize);
    }

    //тип Get /guests/id - получить полное объявление по Id
    @GetMapping(value = "/{id}")
    public AdvertDTO getAdvertByID(@PathVariable(value = "id") Long id) throws NoSuchIdException {
        return advertService.getFullAdvertByID(id);
    }

    //тип Get /guests/id - получить полное объявление по Id
    @GetMapping(value = "/sorted")
    public List<AdvertDTO> getAllSortedAdverts (
            @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return advertService.getAllSortedAdverts(pageNumber, pageSize);
    }

    //тип Post /adverts/JSON добавить новую запись
     @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> addNewAdvert (@RequestBody AdvertDTO advertDTO) {
        advertService.addNewAdvert(advertDTO);
        return ResponseEntity.ok("advert saved");
    }



    //тип Delete /rooms/id удалить запись по Id
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAdvert(@PathVariable(value = "id") Long id) throws NoSuchIdException {

        advertService.deleteAdvert(advertService.findById(id));
    }

    //тип Put /adverts/JSON обновить запись
     @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateAdvert (@RequestBody AdvertDTO advertDTO) {
        advertService.update(advertDTO);
    }

}
