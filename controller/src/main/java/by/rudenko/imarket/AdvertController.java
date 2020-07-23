package by.rudenko.imarket;

import by.rudenko.imarket.dto.AdvertDTO;
import by.rudenko.imarket.dto.AdvertShortDTO;
import by.rudenko.imarket.exception.NoSuchIdException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for adverts requests (get, post, delete, etc.)
 *
 * @author Dmitry Rudenko
 * @version 1.0
 */
@RestController
@RequestMapping(value = {"/adverts"})//
public class AdvertController {

    private final AdvertService advertService;
    //количество записей на страницу из файла свойств
    @Value("${defaultPageSize}")
    public Integer defaultPageSize;

    public AdvertController(final AdvertService advertService) {
        this.advertService = advertService;
    }

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

    @GetMapping(value = "/count")
    public Long advertCount() {
        return advertService.entityCount();
    }

    @GetMapping(value = "/full")
    public List<AdvertDTO> getAllAdverts(
            @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return advertService.getFullAdvertsList(pageNumber, pageSize);
    }

    @GetMapping(value = "/{id}")
    public AdvertDTO getAdvertByID(@PathVariable(value = "id") Long id) throws NoSuchIdException {
        return advertService.getFullAdvertByID(id);
    }

    @GetMapping(value = "/sorted")
    public List<AdvertShortDTO> getAllSortedAdverts(
            @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(value = "topic", required = false) String topic,
            @RequestParam(value = "date", required = false) String date,
            @RequestParam(value = "price", required = false) String price,
            @RequestParam(value = "userRank", required = false) boolean userRank) {
        if (topic != null) {
            return advertService.getAllSortedAdvertsByTopic(topic, pageNumber, pageSize);
        }
        if (userRank) {
            return advertService.getAllSortedAdvertsByUserRank(pageNumber, pageSize);
        }

        return advertService.getAllSortedAdverts(pageNumber, pageSize);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> addNewAdvert(@RequestBody AdvertShortDTO advertShortDTO) {
        advertService.addNewAdvert(advertShortDTO);
        return ResponseEntity.ok("advert saved");
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAdvert(@PathVariable(value = "id") Long id) throws NoSuchIdException {

        advertService.deleteAdvert(advertService.findById(id));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> updateAdvert(@RequestBody AdvertShortDTO advertShortDTO) {
        advertService.update(advertShortDTO);
        return ResponseEntity.ok("advert updated");
    }

}
