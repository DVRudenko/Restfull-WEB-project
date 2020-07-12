package by.rudenko.imarket.model;

import by.rudenko.imarket.enumes.Enumes;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;


/**
 * класс описывает модель Пользователя User
 */


@javax.persistence.Entity
@Table(name = "users")
public class User implements Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Enumes.UserRole role;

    //для избежания циклической зависимости
    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Profile profile;

    public User() {
    }

    public User(Long id, String login, String password, Enumes.UserRole role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
    }

/*    public User(Long id, String login, String password, Enumes.UserRole role, Profile profile) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
        this.profile = profile;
    }*/


    @Override
    public Long getId() {
        return id;
    }

    @Override
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

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
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
        if (!(o instanceof User)) return false;
        User user = (User) o;
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
