package by.rudenko.imarket;

import by.rudenko.imarket.dto.UserDTO;
import by.rudenko.imarket.exception.NoSuchIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

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
    public UserDTO getUserById(@PathVariable(value = "id") Long id) throws NoSuchIdException {
        return userService.findById(id);
    }

    //тип Get /guests/count - получить количество строк в таблице (пользователей)
    @GetMapping(value = "/count")
    public Long getUsersCount() {
        return userService.entityCount();
    }

    //тип Post /users/JSON добавить новую запись

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> addNewUser(@RequestBody UserDTO userDTO) {
        userService.addNewUser(userDTO);
        return ResponseEntity.ok("user saved");
    }


    //тип Delete /rooms/id удалить запись по Id
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long id) throws NoSuchIdException {
        userService.deleteUser(userService.findById(id));
        return ResponseEntity.ok("user deleted");
    }

    //тип Put /users/JSON обновить запись
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@RequestBody UserDTO userDTO) {
        userService.update(userDTO);
    }

}
