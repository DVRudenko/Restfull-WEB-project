package by.rudenko.imarket.exception;

/**
 * класс моего исключения
 */
public class NoSuchIdException extends RuntimeException {

    public NoSuchIdException(String exception) {
        super(exception);
    }
}
