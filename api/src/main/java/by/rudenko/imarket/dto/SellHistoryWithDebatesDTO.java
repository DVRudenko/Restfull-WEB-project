//package by.rudenko.imarket.dto;
//
//import by.rudenko.imarket.model.Debate;
//import by.rudenko.imarket.model.SellHistory;
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//
//import java.util.List;
//
//
///**
// * класс описывает DTO Истории продаж  SellHistory
// */
//
//@JsonInclude(JsonInclude.Include.NON_NULL)
//public class SellHistoryWithDebatesDTO extends SellHistory {
//
//    @JsonManagedReference
//    private List<Debate> debates;
//
//    public List<Debate> getDebates() {
//        return debates;
//    }
//
//    public void setDebates(List<Debate> debates) {
//        this.debates = debates;
//    }
//
//}
