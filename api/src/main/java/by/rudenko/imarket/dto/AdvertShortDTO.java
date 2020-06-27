package by.rudenko.imarket.dto;

import by.rudenko.imarket.model.AdvertRank;
import by.rudenko.imarket.model.AdvertTopic;
import by.rudenko.imarket.model.User;
import by.rudenko.imarket.utils.Enumes;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;


/**
 * класс описывает DTO Объявления  Advert
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdvertShortDTO {

    private Long id;
    @JsonIgnore
    private Long userId;
    @JsonIgnore
    private Long advertTopicId;
    @JsonIgnore
    private Long advertRankId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAdvertTopicId() {
        return advertTopicId;
    }

    public void setAdvertTopicId(Long advertTopicId) {
        this.advertTopicId = advertTopicId;
    }

    public Long getAdvertRankId() {
        return advertRankId;
    }

    public void setAdvertRankId(Long advertRankId) {
        this.advertRankId = advertRankId;
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
