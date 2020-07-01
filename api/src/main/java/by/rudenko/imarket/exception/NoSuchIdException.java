package by.rudenko.imarket.exception;

/**
 * класс моего исключения
 */
public class NoSuchIdException extends Exception {

    public NoSuchIdException() {

    }

    public NoSuchIdException(String exception) {
        super(exception);
    }
}
