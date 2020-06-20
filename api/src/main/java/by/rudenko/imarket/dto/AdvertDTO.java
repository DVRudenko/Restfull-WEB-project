package by.rudenko.imarket.dto;

import by.rudenko.imarket.model.AdvertRank;
import by.rudenko.imarket.model.AdvertTopic;
import by.rudenko.imarket.model.User;
import by.rudenko.imarket.utils.Enumes;
import com.fasterxml.jackson.annotation.*;

import java.time.LocalDate;


/**
 * класс описывает DTO Объявления  Advert
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdvertDTO {

    private Long id;
    private User user;
    private AdvertTopic advertTopic;
    private AdvertRank advertRank;
    private Enumes.AdverType adverType;
    private String advText;
    private int advPrice;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate advDate;
    private Enumes.AdverStatus adverStatus;

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

    public Enumes.AdverType getAdverType() {
        return adverType;
    }

    public void setAdverType(Enumes.AdverType adverType) {
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

    public Enumes.AdverStatus getAdverStatus() {
        return adverStatus;
    }

    public void setAdverStatus(Enumes.AdverStatus adverStatus) {
        this.adverStatus = adverStatus;
    }


}
