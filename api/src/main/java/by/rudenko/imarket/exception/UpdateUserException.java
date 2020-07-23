package by.rudenko.imarket.exception;

/**
 * UpdateUserException class to use in Exception report
 *
 * @author Dmitry Rudenko
 * @version 1.0
 */
public class UpdateUserException extends RuntimeException {

    public UpdateUserException() {
    }

    public UpdateUserException(String exception) {
        super(exception);
    }
}
