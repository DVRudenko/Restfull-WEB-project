package by.rudenko.imarket.model;

import javax.persistence.*;
import java.util.Objects;


/**
 * Coupon class with Coupon Entity model to use in project
 *
 * @author Dmitry Rudenko
 * @version 1.0
 */

@javax.persistence.Entity
@Table(name = "coupons")
public class Coupon implements Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "discount")
    private int discount;

    @Column(name = "coupon_name")
    private String couponName;


    public Coupon() {
    }

    public Coupon(Long id, User user, int discount, String couponName) {
        this.id = id;
        this.user = user;
        this.discount = discount;
        this.couponName = couponName;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coupon)) return false;
        Coupon coupon = (Coupon) o;
        return getDiscount() == coupon.getDiscount() &&
                getId().equals(coupon.getId()) &&
                getUser().equals(coupon.getUser()) &&
                getCouponName().equals(coupon.getCouponName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser(), getDiscount(), getCouponName());
    }
}
