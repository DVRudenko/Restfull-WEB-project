
package by.rudenko.imarket.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;


/**
 * класс описывает Профили пользователя Profiles и его методы
 */

@javax.persistence.Entity
@Table(name = "profiles")
public class Profiles implements Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "city")
    private String city;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "money_balance")
    private int moneyBalance;

    @Column(name = "user_rank")
    private int userRank;



    public Profiles() {
    }




    public Profiles(long id, User user, LocalDate dateOfBirth, String city, String avatar, int moneyBalance, int userRank) {
        this.id = id;
        this.user = user;
        this.dateOfBirth = dateOfBirth;
        this.city = city;
        this.avatar = avatar;
        this.moneyBalance = moneyBalance;
        this.userRank = userRank;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getMoneyBalance() {
        return moneyBalance;
    }

    public void setMoneyBalance(int moneyBalance) {
        this.moneyBalance = moneyBalance;
    }

    public int getUserRank() {
        return userRank;
    }

    public void setUserRank(int userRank) {
        this.userRank = userRank;
    }


    @Override
    public String toString() {
        return "Profiles{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", city='" + city + '\'' +
                ", avatar='" + avatar + '\'' +
                ", moneyBalance=" + moneyBalance +
                ", userRank=" + userRank +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Profiles)) return false;
        Profiles profiles = (Profiles) o;
        return getId() == profiles.getId() &&
                getUser().equals(profiles.getUser()) &&
                Objects.equals(getFirstName(), profiles.getFirstName()) &&
                Objects.equals(getLastName(), profiles.getLastName()) &&
                Objects.equals(getDateOfBirth(), profiles.getDateOfBirth()) &&
                Objects.equals(getCity(), profiles.getCity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser(), getFirstName(), getLastName(), getDateOfBirth(), getCity());
    }
}
