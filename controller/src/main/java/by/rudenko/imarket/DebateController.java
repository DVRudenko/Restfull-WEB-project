package by.rudenko.imarket;

import by.rudenko.imarket.dto.DebateDTO;
import by.rudenko.imarket.exception.NoSuchIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/debates")
public class DebateController<T extends DebateDTO> {

    private final DebateService debateService;

    //TODO как добавить зависимость из модуля Launcher без циклической ссылки???
    //private final IMarketConfig iMarketConfig;
    //IMarketConfig.defaultPageSize //количество записей на страницу по умолчанию (из файла свойств)

    public DebateController(final DebateService debateService) {
        this.debateService = debateService;
    }


    //тип Get /debates/ - получить весь список с пагинацией
    @GetMapping
    public List<DebateDTO> getAllDebates(
            @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return debateService.getFullDebatesList(pageNumber, pageSize);
    }

    //тип Get /guests/count - получить количество строк в таблице (пользователей)
    @GetMapping(value = "/count")
    public Long getUsersCount()  {
        return debateService.entityCount();
    }

    //тип Get /guests/id - получить сущность по Id
    @GetMapping(value = "/{id}")
    public DebateDTO getAllDebates(@PathVariable(value = "id") Long id) throws NoSuchIdException {
        return debateService.findById(id);
    }

    //тип Post /debates/JSON добавить новую запись
    // TODO посмотреть как отрабатывает возврат ответа
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> addNewDebate (@RequestBody DebateDTO debateDTO) {
        debateService.addNewDebate(debateDTO);
        return ResponseEntity.ok("debate saved");
    }



    //тип Delete /rooms/id удалить запись по Id
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteDebate(@PathVariable(value = "id") Long id) throws NoSuchIdException {

        debateService.deleteDebate(debateService.findById(id));
    }

    //тип Put /debates/JSON обновить запись
    //TODO как лучше через POST или PUT
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateDebate (@RequestBody DebateDTO debateDTO) {
        debateService.update(debateDTO);
    }

}
