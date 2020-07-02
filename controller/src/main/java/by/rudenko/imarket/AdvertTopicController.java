package by.rudenko.imarket;

import by.rudenko.imarket.dto.AdvertTopicDTO;
import by.rudenko.imarket.exception.NoSuchIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/advertTopics")
public class AdvertTopicController<T extends AdvertTopicDTO> {

    private final AdvertTopicService advertTopicService;

    //TODO как добавить зависимость из модуля Launcher без циклической ссылки???
    //private final IMarketConfig iMarketConfig;
    //IMarketConfig.defaultPageSize //количество записей на страницу по умолчанию (из файла свойств)

    public AdvertTopicController(final AdvertTopicService advertTopicService) {
        this.advertTopicService = advertTopicService;
    }


    //тип Get /advertTopics/ - получить весь список с пагинацией
    @GetMapping
    public List<AdvertTopicDTO> getAllAdvertTopics(
            @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return advertTopicService.getAllAdvertTopicsList(pageNumber, pageSize);
    }

    //тип Get /count - получить количество строк в таблице
    @GetMapping(value = "/count")
    public Long getUsersCount()  {
        return advertTopicService.entityCount();
    }

    //тип Get /guests/id - получить сущность по Id
    @GetMapping(value = "/{id}")
    public AdvertTopicDTO getAllAdvertTopics(@PathVariable(value = "id") Long id) throws NoSuchIdException {
        return advertTopicService.findById(id);
    }

    //тип Post /advertTopics/JSON добавить новую запись
    // TODO посмотреть как отрабатывает возврат ответа
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> addNewAdvertTopic (@RequestBody AdvertTopicDTO advertTopicDTO) {
        advertTopicService.addNewAdvertTopic(advertTopicDTO);
        return ResponseEntity.ok("advertTopic saved");
    }



    //тип Delete /rooms/id удалить запись по Id
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAdvertTopic(@PathVariable(value = "id") Long id) throws NoSuchIdException {

        advertTopicService.deleteAdvertTopic(advertTopicService.findById(id));
    }

    //тип Put /advertTopics/JSON обновить запись
    //TODO как лучше через POST или PUT
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateAdvertTopic (@RequestBody AdvertTopicDTO advertTopicDTO) {
        advertTopicService.update(advertTopicDTO);
    }

}
