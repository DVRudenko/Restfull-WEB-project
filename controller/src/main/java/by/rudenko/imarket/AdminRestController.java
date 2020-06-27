package by.rudenko.imarket;

import by.rudenko.imarket.dto.UserDTO;
import by.rudenko.imarket.exception.NoSuchIdException;
import by.rudenko.imarket.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for ROLE_ADMIN requests.
 *
 * @author Dmitry Rudenko
 * @version 1.0
 */

@RestController
@RequestMapping(value = "/admin/")
public class AdminRestController {

    private final UserService userService;

    @Autowired
    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "users/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(name = "id") Long id) throws NoSuchIdException {
        UserDTO user = userService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        // у нас сразу возвращает юзерДТО
        //UserDTO result = UserDTO.fromUser(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
