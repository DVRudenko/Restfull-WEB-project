package by.rudenko.imarket.model;

import by.rudenko.imarket.utils.Enumes;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * класс описывает модель Истории продаж  SellHistory
 */

@javax.persistence.Entity
@Table(name = "sell_histories")
public class SellHistory implements Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id")
    private User buyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private User seller;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adv_id")
    private Advert advert;

    @Column(name = "sell_status")
    @Enumerated(EnumType.STRING)
    private Enumes.SellStatus sellStatus;

    @Column(name = "sell_date")
    private LocalDate sellDate;

    @Column(name = "sell_price")
    private int sellPrice;


    public SellHistory() {
    }

    public SellHistory(Long id, User buyer, User seller, Advert advert, Enumes.SellStatus sellStatus, LocalDate sellDate, int sellPrice) {
        this.id = id;
        this.buyer = buyer;
        this.seller = seller;
        this.advert = advert;
        this.sellStatus = sellStatus;
        this.sellDate = sellDate;
        this.sellPrice = sellPrice;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
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

    @Override
    public String toString() {
        return "SellHistory{" +
                "id=" + id +
                ", buyer=" + buyer +
                ", seller=" + seller +
                ", advert=" + advert +
                ", sellStatus=" + sellStatus +
                ", sellDate=" + sellDate +
                ", sellPrice=" + sellPrice +
                '}';
    }
}
