
package by.rudenko.imarket.model;

import javax.persistence.*;


/**
 * класс описывает Комментарии к объявлениям Comments и его методы
 */

@javax.persistence.Entity
@Table(name = "comments")
public class Comments implements Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "adv_id")
    private Advert advert;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "user_id")
    private User user;


    public Comments() {
    }

    public Comments(long id, User user, Advert advert) {
        this.id = id;
        this.user = user;
        this.advert = advert;
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

    public Advert getAdvert() {
        return advert;
    }

    public void setAdvert(Advert advert) {
        this.advert = advert;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "id=" + id +
                ", adverts=" + advert +
                ", user=" + user +
                '}';
    }

    @ManyToOne(optional = false)
    private AdvertWithComments advertWithComments;

    public AdvertWithComments getAdvertWithComments() {
        return advertWithComments;
    }

    public void setAdvertWithComments(AdvertWithComments advertWithComments) {
        this.advertWithComments = advertWithComments;
    }
}
