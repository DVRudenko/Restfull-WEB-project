package by.rudenko.imarket.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;


/**
 * класс описывает модель Дебаты по проданным товарам Debates
 */

@javax.persistence.Entity
@Table(name = "debates")
public class Debate implements Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL//, mappedBy = "adverts"
//    )
//    private List<Advert> advert;

    public Debate() {
    }

    public Debate(Long id, User user, SellHistory sellHistory, LocalDate debateDate, String debateText) {
        this.id = id;
        this.user = user;
        this.sellHistory = sellHistory;
        this.debateDate = debateDate;
        this.debateText = debateText;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Debate)) return false;
        Debate debate = (Debate) o;
        return getId().equals(debate.getId()) &&
                getSellHistory().equals(debate.getSellHistory()) &&
                getUser().equals(debate.getUser()) &&
                getDebateDate().equals(debate.getDebateDate()) &&
                Objects.equals(getDebateText(), debate.getDebateText());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSellHistory(), getUser(), getDebateDate(), getDebateText());
    }
}
