package by.rudenko.imarket.enumes;

public class Enumes {

    /**
     * Enumes class to use in project
     *
     * @author Dmitry Rudenko
     * @version 1.0
     */

    // роль пользователя: юзер или администратор
    public enum UserRole {ROLE_USER, ROLE_ADMIN}

    // ранг объявления: для сортировке при показе объявления
    public enum RankName {USUAL, PRIOR, VIP}

    // тип объявления: куплю, продам, сниму
    public enum AdverType {BUY, SELL, RENT}

    // статус объявления: новое/продано/удалено
    public enum AdverStatus {NEW, SOLD, DELETED}

    // статус продаж: оплачено/выслано/получено/отменено
    public enum SellStatus {PAID, SEND, RECEIVED, CANCELLED}
}