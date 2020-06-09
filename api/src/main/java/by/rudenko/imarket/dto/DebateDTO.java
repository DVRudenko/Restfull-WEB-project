package by.rudenko.imarket.model;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * класс описывает модель Дебаты по проданным товарам Debates
 */

@javax.persistence.Entity
@Table(name = "debates")
public class Debate implements Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sell_history_id")
    private SellHistory sellHistory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "debate_date")
    private LocalDate debateDate;

    @Column(name = "debate_text")
    private String debateText;

    public Debate() {
    }

    public Debate(long id, User user, SellHistory sellHistory, LocalDate debateDate, String debateText) {
        this.id = id;
        this.user = user;
        this.sellHistory = sellHistory;
        this.debateDate = debateDate;
        this.debateText = debateText;

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

    public SellHistory getSellHistory() {
        return sellHistory;
    }

    public void setSellHistory(SellHistory sellHistory) {
        this.sellHistory = sellHistory;
    }

    public LocalDate getDebateDate() {
        return debateDate;
    }

    public void setDebateDate(LocalDate debateDate) {
        this.debateDate = debateDate;
    }

    public String getDebateText() {
        return debateText;
    }

    public void setDebateText(String debateText) {
        this.debateText = debateText;
    }

    @Override
    public String toString() {
        return "Debate{" +
                "id=" + id +
                ", sellHistory=" + sellHistory +
                ", user=" + user +
                ", debateDate=" + debateDate +
                ", debateText='" + debateText + '\'' +
                '}';
    }
}
