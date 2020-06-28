package by.rudenko.imarket.dto;

import by.rudenko.imarket.utils.Enumes;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;


/**
 * класс описывает DTO Объявления  Advert
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdvertShortDTO {

    private Long id;
    private Long userId;
    private Long advertTopicId;
    private Long advertRankId;
    private Enumes.AdverType advType;
    private String advText;
    private int advPrice;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate advDate;
    private Enumes.AdverStatus advStatus;

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

    public Enumes.AdverType getAdvType() {
        return advType;
    }

    public void setAdvType(Enumes.AdverType advType) {
        this.advType = advType;
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

    public void setAdvStatus(Enumes.AdverStatus advStatus) {
        this.advStatus = advStatus;
    }


}
