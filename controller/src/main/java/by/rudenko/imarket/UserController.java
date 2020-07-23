package by.rudenko.imarket;

import by.rudenko.imarket.dto.UserDTO;
import by.rudenko.imarket.exception.DeleteUserException;
import by.rudenko.imarket.exception.NoSuchIdException;
import by.rudenko.imarket.exception.UpdateUserException;
import by.rudenko.imarket.jwt.JwtUser;
import by.rudenko.imarket.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;

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
        return userService.getCount();
    }

    //тип Post /users/JSON добавить новую запись

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> addNewUser(@RequestBody UserDTO userDTO) {
        //проверяем пользователя на дублирование логина
        String newLogin = userDTO.getLogin();
        if (userService.findByUsername(newLogin) != null) {
            return ResponseEntity.status(CONFLICT).body("Login is already exist");
        }
        userService.addNewUser(userDTO);
        return ResponseEntity.ok("user saved");
    }


    //тип Delete /rooms/id удалить запись по Id
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long id,
                                        @AuthenticationPrincipal JwtUser user) throws NoSuchIdException, DeleteUserException {
        //проверяем id User
        if (id == user.getId()) {
            userService.deleteUser(userService.findById(id));
            return ResponseEntity.ok("user deleted");
        } else {
            throw new DeleteUserException("Can't delete other user");
        }
    }

    //тип Put /users/JSON обновить запись
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> updateUser(@RequestBody UserDTO userDTO, @AuthenticationPrincipal JwtUser user) throws UpdateUserException {
        //проверяем id User
        if (userDTO.getId() == user.getId()) {
            userService.update(userDTO);
            return ResponseEntity.ok("user updated");
        } else {
            throw new UpdateUserException("Can't update other user");


        }

    }
}
