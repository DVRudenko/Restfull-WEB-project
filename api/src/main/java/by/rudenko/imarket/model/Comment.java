package by.rudenko.imarket.model;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * класс описывает модель Комментария к объявлениям Comments
 */

@javax.persistence.Entity
@Table(name = "comments")
public class Comments implements Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adv_id")
    private Advert advert;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "comment_date")
    private LocalDate commentDate;

    @Column(name = "comment_text")
    private String commentText;

    public Comments() {
    }

    public Comments(long id, User user, Advert advert, LocalDate commentDate, String commentText) {
        this.id = id;
        this.user = user;
        this.advert = advert;
        this.commentDate = commentDate;
        this.commentText = commentText;
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

    public LocalDate getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(LocalDate commentDate) {
        this.commentDate = commentDate;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "id=" + id +
                ", advert=" + advert +
                ", user=" + user +
                ", commentDate=" + commentDate +
                ", commentText='" + commentText + '\'' +
                '}';
    }
}
