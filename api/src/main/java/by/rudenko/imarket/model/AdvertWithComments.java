/*
package by.rudenko.imarket.model;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;


*/
/**
 * класс описывает модель Объявления с комментариями  AdvertWithComments
 *//*


@javax.persistence.Entity
@Table(name = "adverts")
public class AdvertWithComments extends Advert {


    //TODO поверить маппинг (adverts)!!!
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "adverts")
    private List<Comment> comments;

    public AdvertWithComments() {
    }

    public AdvertWithComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "AdvertWithComments{" +
                "comments=" + comments +
                '}';
    }
}
*/
