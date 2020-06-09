
package by.rudenko.imarket.model;

import by.rudenko.imarket.utils.Utils;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;


/**
 * класс описывает Скидочные купоны  Coupons и его методы
 */

@javax.persistence.Entity
@Table(name = "coupons")
public class Coupon implements Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "discount")
    private int discount;

    @Column(name = "coupon_name")
    private String couponName;


    public Coupon() {
    }

    public Coupon(long id, User user, int discount, String couponName) {
        this.id = id;
        this.user = user;
        this.discount = discount;
        this.couponName = couponName;
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

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "id=" + id +
                ", user=" + user +
                ", discount=" + discount +
                ", couponName='" + couponName + '\'' +
                '}';
    }
}
