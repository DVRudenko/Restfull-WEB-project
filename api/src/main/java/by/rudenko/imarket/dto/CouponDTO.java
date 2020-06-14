package by.rudenko.imarket.dto;

import by.rudenko.imarket.model.User;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;


/**
 * класс описывает DTO Скидочного купона  Coupons
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CouponDTO  {

    private Long id;
    private User user;
    private int discount;
    private String couponName;

    public Long getId() {
        return id;
    }

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


}
