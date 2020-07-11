package by.rudenko.imarket;

import by.rudenko.imarket.jwt.JwtUser;
import by.rudenko.imarket.jwt.JwtUserFactory;
import by.rudenko.imarket.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service

public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    private static final Logger LOGGER = LogManager.getLogger("imarket");

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }

        JwtUser jwtUser = JwtUserFactory.create(user);
        LOGGER.info("IN loadUserByUsername - user with username: {} successfully loaded", username);
        return jwtUser;
    }
}
