package by.rudenko.imarket.dto;

import by.rudenko.imarket.enumes.Enumes;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Advert Rank DTO class to use in RestAPI
 *
 * @author Dmitry Rudenko
 * @version 1.0
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdvertRankDTO {

    private Long id;
    private int rankPrice;
    private Enumes.RankName rankName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRankPrice() {
        return rankPrice;
    }

    public void setRankPrice(int rankPrice) {
        this.rankPrice = rankPrice;
    }

    public Enumes.RankName getRankName() {
        return rankName;
    }

    public void setRankName(Enumes.RankName rankName) {
        this.rankName = rankName;
    }

}
