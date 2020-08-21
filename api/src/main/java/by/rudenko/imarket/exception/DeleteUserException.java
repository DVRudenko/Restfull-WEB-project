package by.rudenko.imarket.exception;

/**
 * DeleteUserException class to use in Exception report
 *
 * @author Dmitry Rudenko
 * @version 1.0
 */
public class DeleteUserException extends RuntimeException {

    public DeleteUserException() {
    }

    public DeleteUserException(String exception) {
        super(exception);
    }
}
