package by.rudenko.imarket.jwt;


import by.rudenko.imarket.model.User;
import by.rudenko.imarket.utils.Enumes;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of Factory Method for class {@link JwtUser}.
 *
 * @author Dmitry Rudenko
 * @version 1.0
 */


public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getLogin(),
                user.getPassword(),
                mapToGrantedAuthorities(user.getRole())
        );


    }
        private static GrantedAuthority mapToGrantedAuthorities ( Enumes.UserRole userRoles) {
            return new SimpleGrantedAuthority(userRoles.name());
        }


}
