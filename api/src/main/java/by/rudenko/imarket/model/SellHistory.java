
package by.rudenko.imarket.model;

import by.rudenko.imarket.utils.Utils;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * класс описывает Раздел Объявления  AdvertTopic и его методы
 */

@javax.persistence.Entity
@Table(name = "adverts")
public class Advert implements Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "adv_topic_id")
    private AdvertTopic advertTopic;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "adv_rank_id")
    private AdvertRank  advertRank;

    @Column(name = "adv_type")
    @Enumerated(EnumType.STRING)
    private Utils.AdverType adverType;

    @Column(name = "adv_text")
    private String advText;

    @Column(name = "adv_price")
    private int advPrice;

    @Column(name = "adv_date")
    private LocalDate advDate;

    @Column(name = "adv_status")
    @Enumerated(EnumType.STRING)
    private Utils.AdverStatus adverStatus;


    public Advert() {
    }

    public Advert(User user, AdvertTopic advertTopic, AdvertRank advertRank, Utils.AdverType adverType,
                  String advText, int advPrice, LocalDate advDate, Utils.AdverStatus adverStatus) {
        this.user = user;
        this.advertTopic = advertTopic;
        this.advertRank = advertRank;
        this.adverType = adverType;
        this.advText = advText;
        this.advPrice = advPrice;
        this.advDate = advDate;
        this.adverStatus = adverStatus;
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

    public AdvertTopic getAdvertTopic() {
        return advertTopic;
    }

    public void setAdvertTopic(AdvertTopic advertTopic) {
        this.advertTopic = advertTopic;
    }

    public AdvertRank getAdvertRank() {
        return advertRank;
    }

    public void setAdvertRank(AdvertRank advertRank) {
        this.advertRank = advertRank;
    }

    public Utils.AdverType getAdverType() {
        return adverType;
    }

    public void setAdverType(Utils.AdverType adverType) {
        this.adverType = adverType;
    }

    public String getAdvText() {
        return advText;
    }

    public void setAdvText(String advText) {
        this.advText = advText;
    }

    public int getAdvPrice() {
        return advPrice;
    }

    public void setAdvPrice(int advPrice) {
        this.advPrice = advPrice;
    }

    public LocalDate getAdvDate() {
        return advDate;
    }

    public void setAdvDate(LocalDate advDate) {
        this.advDate = advDate;
    }

    public Utils.AdverStatus getAdverStatus() {
        return adverStatus;
    }

    public void setAdverStatus(Utils.AdverStatus adverStatus) {
        this.adverStatus = adverStatus;
    }

    @Override
    public String toString() {
        return "Adverts{" +
                "id=" + id +
                ", user=" + user +
                ", advertTopic=" + advertTopic +
                ", advertRank=" + advertRank +
                ", adverType=" + adverType +
                ", advText='" + advText + '\'' +
                ", advPrice=" + advPrice +
                ", advDate=" + advDate +
                ", adverStatus=" + adverStatus +
                '}';
    }
}
