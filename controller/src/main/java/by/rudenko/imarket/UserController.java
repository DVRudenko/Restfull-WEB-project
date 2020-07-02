package by.rudenko.imarket;

import by.rudenko.imarket.dto.UserDTO;
import by.rudenko.imarket.exception.NoSuchIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController<T extends UserDTO> {

    private final UserService userService;

    //TODO как добавить зависимость из модуля Launcher без циклической ссылки???
    //private final IMarketConfig iMarketConfig;
    //IMarketConfig.defaultPageSize //количество записей на страницу по умолчанию (из файла свойств)

    public UserController(final UserService userService) {
        this.userService = userService;
    }


    //тип Get /users/ - получить весь список с пагинацией
    @GetMapping
    public List<UserDTO> getAllUsers(
            @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return userService.getAllUsersList(pageNumber, pageSize);
    }

    //тип Get /guests/id - получить сущность по Id
    @GetMapping(value = "/{id}")
    public UserDTO getAllUsers(@PathVariable(value = "id") Long id) throws NoSuchIdException {
        return userService.findById(id);
    }

    //тип Get /guests/count - получить количество строк в таблице (пользователей)
    @GetMapping(value = "/count")
    public Long getUsersCount()  {
        return userService.entityCount();
    }

    //тип Post /users/JSON добавить новую запись
    // TODO посмотреть как отрабатывает возврат ответа
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> addNewUser (@RequestBody UserDTO userDTO) {
        userService.addNewUser(userDTO);
        return ResponseEntity.ok("user saved");
    }



    //тип Delete /rooms/id удалить запись по Id
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable(value = "id") Long id) throws NoSuchIdException {

        userService.deleteUser(userService.findById(id));
    }

    //тип Put /users/JSON обновить запись
    //TODO как лучше через POST или PUT
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateUser (@RequestBody UserDTO userDTO) {
        userService.update(userDTO);
    }

}
