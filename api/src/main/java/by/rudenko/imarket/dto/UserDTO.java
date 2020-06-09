package by.rudenko.imarket.model;

import by.rudenko.imarket.utils.Utils;

import javax.persistence.*;
import java.util.Objects;


/**
 * класс описывает модель Пользователя User
 */

@javax.persistence.Entity
@Table(name = "users")
public class UserDTO implements Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Utils.UserRole role;

    public UserDTO() {
    }

    public UserDTO(long id, String login, String password, Utils.UserRole role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
    }


    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
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

    public Utils.UserRole getRole() {
        return role;
    }

    public void setRole(Utils.UserRole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDTO)) return false;
        UserDTO user = (UserDTO) o;
        return getId() == user.getId() &&
                getLogin().equals(user.getLogin()) &&
                getPassword().equals(user.getPassword()) &&
                getRole() == user.getRole();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLogin(), getPassword(), getRole());
    }
}
