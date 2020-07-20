package by.rudenko.imarket.exception;

/**
 * NoSuchIdException class to use in Exception report
 *
 * @author Dmitry Rudenko
 * @version 1.0
 */
public class NoSuchIdException extends RuntimeException {

    public NoSuchIdException(String exception) {
        super(exception);
    }
}
