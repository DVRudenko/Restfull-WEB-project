package by.rudenko.imarket.model;

import by.rudenko.imarket.utils.Enumes;

import javax.persistence.*;


/**
 * класс описывает модель Ранга объявления  AdvertRank
 */

@javax.persistence.Entity
@Table(name = "advert_ranks")
public class AdvertRank implements Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rank_price")
    private int rankPrice;

    @Column(name = "rank_name")
    @Enumerated(EnumType.STRING)
    private Enumes.RankName rankName;

    public AdvertRank() {
    }

    public AdvertRank(Long id, int rankPrice, Enumes.RankName rankName) {
        this.id = id;
        this.rankPrice = rankPrice;
        this.rankName = rankName;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
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


    @Override
    public String toString() {
        return "AdvertRank{" +
                "id=" + id +
                ", rankPrice=" + rankPrice +
                ", rankName='" + rankName + '\'' +
                '}';
    }
}
