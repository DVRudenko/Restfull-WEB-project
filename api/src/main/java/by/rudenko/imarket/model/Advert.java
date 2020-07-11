package by.rudenko.imarket.model;

import by.rudenko.imarket.enumes.Enumes;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;


/**
 * класс описывает модель Объявления  Advert
 */

@javax.persistence.Entity
@Table(name = "adverts")
public class Advert implements Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adv_topic_id")
    private AdvertTopic advertTopic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adv_rank_id")
    private AdvertRank advertRank;

    @Column(name = "adv_type")
    @Enumerated(EnumType.STRING)
    private Enumes.AdverType advType;

    @Column(name = "adv_text")
    private String advText;

    @Column(name = "adv_price")
    private int advPrice;

    @Column(name = "adv_date")
    private LocalDate advDate;

    @Column(name = "adv_status")
    @Enumerated(EnumType.STRING)
    private Enumes.AdverStatus advStatus;


    public Advert() {
    }

    public Advert(User user, AdvertTopic advertTopic, AdvertRank advertRank, Enumes.AdverType advType,
                  String advText, int advPrice, LocalDate advDate, Enumes.AdverStatus advStatus) {
        this.user = user;
        this.advertTopic = advertTopic;
        this.advertRank = advertRank;
        this.advType = advType;
        this.advText = advText;
        this.advPrice = advPrice;
        this.advDate = advDate;
        this.advStatus = advStatus;
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

    public Enumes.AdverType getAdvType() {
        return advType;
    }

    public void setAdvType(Enumes.AdverType adverType) {
        this.advType = adverType;
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

    public Enumes.AdverStatus getAdvStatus() {
        return advStatus;
    }

    public void setAdvStatus(Enumes.AdverStatus adverStatus) {
        this.advStatus = adverStatus;
    }

    @Override
    public String toString() {
        return "Adverts{" +
                "id=" + id +
                ", user=" + user +
                ", advertTopic=" + advertTopic +
                ", advertRank=" + advertRank +
                ", adverType=" + advType +
                ", advText='" + advText + '\'' +
                ", advPrice=" + advPrice +
                ", advDate=" + advDate +
                ", adverStatus=" + advStatus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Advert)) return false;
        Advert advert = (Advert) o;
        return getAdvPrice() == advert.getAdvPrice() &&
                getId().equals(advert.getId()) &&
                getUser().equals(advert.getUser()) &&
                getAdvertTopic().equals(advert.getAdvertTopic()) &&
                getAdvertRank().equals(advert.getAdvertRank()) &&
                getAdvType() == advert.getAdvType() &&
                Objects.equals(getAdvText(), advert.getAdvText()) &&
                getAdvDate().equals(advert.getAdvDate()) &&
                getAdvStatus() == advert.getAdvStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser(), getAdvertTopic(), getAdvertRank(), getAdvType(), getAdvText(), getAdvPrice(), getAdvDate(), getAdvStatus());
    }
}
