package by.rudenko.imarket;

import by.rudenko.imarket.dto.AdvertDTO;
import by.rudenko.imarket.dto.AdvertShortDTO;
import by.rudenko.imarket.exception.NoSuchIdException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//пробуем
@RequestMapping(value = {"/", "/adverts"})
//@RequestMapping("/adverts")
public class AdvertController<T extends AdvertDTO> {

    private final AdvertService advertService;

    public AdvertController(final AdvertService advertService) {
        this.advertService = advertService;
    }

    @Value("${defaultPageSize}")
    public Integer defaultPageSize;
    //количество записей на страницу (по умолчанию 10)
    //public final String defPage = defaultPageSize.toString();
    //TODO defaultValue = defPage - не разрешает*/

    //тип Get /guests/id - получить "краткие" объявления
    @GetMapping
    public List<AdvertShortDTO> getAllShortAdverts (
            @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return advertService.getAllShortAdverts(pageNumber, pageSize);
    }

    //тип Get /count - получить количество строк в таблице
    @GetMapping(value = "/count")
    public Long getUsersCount()  {
        return advertService.entityCount();
    }

    //тип Get /adverts/ - получить весь список объявлений с пагинацией (без сортировки)
    @GetMapping(value = "/full")
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
