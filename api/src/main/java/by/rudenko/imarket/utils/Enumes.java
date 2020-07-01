package by.rudenko.imarket.utils;

public class Enumes {

    //цвета выода на консоль (просто для удобства)
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    // роль пользователя: юзер или администратор
    public enum UserRole {ROLE_USER, ROLE_ADMIN}

    // ранг объявления: для сортировке при показе объявления
    public enum RankName {USUAL, PRIOR, VIP}

    // тип объявления: куплю, продам, сниму
    public enum AdverType {BUY, SELL, RENT}

    // статус объявления: новое/продано/удалено
    public enum AdverStatus {NEW, SOLD, DELETED}

    // статус продаж: новое/продано/удалено
    public enum SellStatus {PAID, SEND, RECEIVED, CANCELLED}
}