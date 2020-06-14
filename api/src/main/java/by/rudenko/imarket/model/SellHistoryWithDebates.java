//package by.rudenko.imarket.model;
//
//import by.rudenko.imarket.utils.Utils;
//
//import javax.persistence.*;
//import java.time.LocalDate;
//import java.util.List;
//
//
///**
// * класс описывает модель Истории продаж  SellHistory
// */
//
//@javax.persistence.Entity
//@Table(name = "sell_histories")
//public class SellHistoryWithDebates extends SellHistory {
//
//    //TODO поверить маппинг (sell_histories)!!!
//    @SuppressWarnings("JpaModelReferenceInspection")
//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "debates")
//    private List<Debate> debates;
//
//
//    public SellHistoryWithDebates() {
//    }
//
//    public SellHistoryWithDebates(List<Debate> debates) {
//        this.debates = debates;
//    }
//
//    public List<Debate> getDebates() {
//        return debates;
//    }
//
//    public void setDebates(List<Debate> debates) {
//        this.debates = debates;
//    }
//
//    @Override
//    public String toString() {
//        return "SellHistoryWithDebates{" +
//                "debates=" + debates +
//                '}';
//    }
//}
