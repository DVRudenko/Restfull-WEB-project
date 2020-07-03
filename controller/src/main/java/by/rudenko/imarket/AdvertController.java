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
@RequestMapping(value = {"/", "/adverts"})
public class AdvertController {

    private final AdvertService advertService;
    //количество записей на страницу из файла свойств
    @Value("${defaultPageSize}")
    public Integer defaultPageSize;

    public AdvertController(final AdvertService advertService) {
        this.advertService = advertService;
    }

    //тип Get - получить "краткие" объявления
    @GetMapping
    public List<AdvertShortDTO> getAllShortAdverts(
            @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
            @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        //проверка на корректность параметров пагинации
        CheckPagination checkPagination =
                new CheckPagination(pageNumber, pageSize,
                        advertService.entityCount(), defaultPageSize)
                        .check();
        pageNumber = checkPagination.getPageNumber();
        pageSize = checkPagination.getPageSize();

        return advertService.getAllShortAdverts(pageNumber, pageSize);
    }

    //тип Get /count - получить количество строк в таблице
    @GetMapping(value = "/count")
    public Long advertCount() {
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

    //тип Get сортированные объявления
    @GetMapping(value = "/sorted")
    public List<AdvertShortDTO> getAllSortedAdverts(
            @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(value = "topic", required = false) String topic) {
        if (topic == null) {
            return advertService.getAllSortedAdverts(pageNumber, pageSize);
        }
        return advertService.getAllSortedAdvertsByTopic(topic, pageNumber, pageSize);
    }

    //тип Post /adverts/JSON добавить новую запись
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> addNewAdvert(@RequestBody AdvertDTO advertDTO) {
        advertService.addNewAdvert(advertDTO);
        return ResponseEntity.ok("advert saved");
    }


    //тип Delete удалить запись по Id
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAdvert(@PathVariable(value = "id") Long id) throws NoSuchIdException {

        advertService.deleteAdvert(advertService.findById(id));
    }

    //тип Put /adverts/JSON обновить запись
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateAdvert(@RequestBody AdvertDTO advertDTO) {
        advertService.update(advertDTO);
    }

}
