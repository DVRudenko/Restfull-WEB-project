package by.rudenko.imarket.dto;

import by.rudenko.imarket.enumes.Enumes;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;

/**
 * Short Advert (without include) DTO class to use in RestAPI
 *
 * @author Dmitry Rudenko
 * @version 1.0
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
    private Enumes.AdverStatus adverStatus;

    public AdvertShortDTO() {
    }

    public AdvertShortDTO(Long id, Long userId, Long advertTopicId, Long advertRankId,
                          Enumes.AdverType advType, String advText, int advPrice, LocalDate advDate, Enumes.AdverStatus adverStatus) {
        this.id = id;
        this.userId = userId;
        this.advertTopicId = advertTopicId;
        this.advertRankId = advertRankId;
        this.advType = advType;
        this.advText = advText;
        this.advPrice = advPrice;
        this.advDate = advDate;
        this.adverStatus = adverStatus;
    }

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

    public Enumes.AdverStatus getAdverStatus() {
        return adverStatus;
    }

    public void setAdverStatus(Enumes.AdverStatus adverStatus) {
        this.adverStatus = adverStatus;
    }


}
