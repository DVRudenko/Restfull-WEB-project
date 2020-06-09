
package by.rudenko.imarket.model;

import by.rudenko.imarket.utils.Utils;

import javax.persistence.*;


/**
 * класс описывает Ранг Объявления  AdvertRank и его методы
 */

@javax.persistence.Entity
@Table(name = "advert_ranks")
public class AdvertRank implements Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "rank_price")
    private int rankPrice;

    @Column(name = "rank_name")
    @Enumerated(EnumType.STRING)
    private Utils.RankName rankName;


    public AdvertRank() {
    }

    public AdvertRank(long id, int rankPrice, Utils.RankName rankName) {
        this.id = id;
        this.rankPrice = rankPrice;
        this.rankName = rankName;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public int getRankPrice() {
        return rankPrice;
    }

    public void setRankPrice(int rankPrice) {
        this.rankPrice = rankPrice;
    }

    public Utils.RankName getRankName() {
        return rankName;
    }

    public void setRankName(Utils.RankName rankName) {
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
