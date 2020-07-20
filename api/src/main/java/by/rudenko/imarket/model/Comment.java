package by.rudenko.imarket.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;


/**
 * Comment class with Comment Entity model to use in project
 *
 * @author Dmitry Rudenko
 * @version 1.0
 */

@javax.persistence.Entity
@Table(name = "comments")
public class Comment implements Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public Comment() {
    }

    public Comment(Long id, User user, Advert advert, LocalDate commentDate, String commentText) {
        this.id = id;
        this.user = user;
        this.advert = advert;
        this.commentDate = commentDate;
        this.commentText = commentText;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        Comment comment = (Comment) o;
        return getId().equals(comment.getId()) &&
                getAdvert().equals(comment.getAdvert()) &&
                getUser().equals(comment.getUser()) &&
                getCommentDate().equals(comment.getCommentDate()) &&
                Objects.equals(getCommentText(), comment.getCommentText());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAdvert(), getUser(), getCommentDate(), getCommentText());
    }
}
