package by.rudenko.imarket.dto;

import by.rudenko.imarket.utils.Enumes;
import com.fasterxml.jackson.annotation.JsonInclude;


/**
 * класс описывает DTO Пользователя User
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO implements by.rudenko.imarket.model.Entity {

    private Long id;
    private String login;
    private String password;
    private Enumes.UserRole role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Enumes.UserRole getRole() {
        return role;
    }

    public void setRole(Enumes.UserRole role) {
        this.role = role;
    }


}
