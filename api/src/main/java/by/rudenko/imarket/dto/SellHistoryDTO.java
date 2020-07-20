package by.rudenko.imarket.dto;

import by.rudenko.imarket.model.Advert;
import by.rudenko.imarket.model.User;
import by.rudenko.imarket.enumes.Enumes;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;


/**
 * SellHistory DTO class to use in RestAPI
 *
 * @author Dmitry Rudenko
 * @version 1.0
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SellHistoryDTO {

    private Long id;
    private User buyer;
    private User seller;
    private Advert advert;
    private Enumes.SellStatus sellStatus;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate sellDate;
    private int sellPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public Advert getAdvert() {
        return advert;
    }

    public void setAdvert(Advert advert) {
        this.advert = advert;
    }

    public Enumes.SellStatus getSellStatus() {
        return sellStatus;
    }

    public void setSellStatus(Enumes.SellStatus sellStatus) {
        this.sellStatus = sellStatus;
    }

    public LocalDate getSellDate() {
        return sellDate;
    }

    public void setSellDate(LocalDate sellDate) {
        this.sellDate = sellDate;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }

}
