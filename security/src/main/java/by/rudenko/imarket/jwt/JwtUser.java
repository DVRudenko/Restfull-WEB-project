package by.rudenko.imarket.jwt;

import by.rudenko.imarket.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

/**
 * Spring Security wrapper for class {@link User}.
 *
 * @author Dmitry Rudenko
 * @version 1.0
 */


public class JwtUser implements UserDetails {

    private final Long id;
    private final String username;
    private final String password;
    private final GrantedAuthority authorities;


    public JwtUser(Long id,
                   String username,
                   String password,
                   GrantedAuthority authorities
    ) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(authorities);
    }

    @JsonIgnore
    @Override
    public String getPassword() {

        return password;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
