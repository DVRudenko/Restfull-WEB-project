package by.rudenko.imarket.jwt;

import org.springframework.security.core.AuthenticationException;

/**
 * Authetication exception for iMarket application.
 *
 * @author Dmitry Rudenko
 * @version 1.0
 */

public class JwtAuthenticationException extends AuthenticationException {
    public JwtAuthenticationException(String msg, Throwable t) {

        super(msg, t);
    }

    public JwtAuthenticationException(String msg) {

        super(msg);
    }
}