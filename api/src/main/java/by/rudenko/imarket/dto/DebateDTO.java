package by.rudenko.imarket.dto;

import by.rudenko.imarket.model.SellHistory;
import by.rudenko.imarket.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;


/**
 * класс описывает DTO Дебаты по проданным товарам Debates
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DebateDTO {

    private Long id;
    private SellHistory sellHistory;
    private User user;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate debateDate;
    private String debateText;

    public Long getId() {
        return id;
    }

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
}
