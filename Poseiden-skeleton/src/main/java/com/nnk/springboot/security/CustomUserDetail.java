package com.nnk.springboot.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.nnk.springboot.domain.User;

/**
 * Custom implementation of {@link UserDetails} to represent the authenticated user's details.
 * This class wraps a {@link User} object and provides the necessary methods required by Spring Security.
 */
public class CustomUserDetail implements UserDetails {

    private static final long serialVersionUID = 1L;
    private User user;

    /**
     * Constructs a new {@code CustomUserDetail} with the given {@link User}.
     * 
     * @param user the {@link User} entity to wrap.
     */
    public CustomUserDetail(User user) {
        this.user = user;
    }

    /**
     * Returns the authorities granted to the user. In this implementation, 
     * it returns the user's role.
     * 
     * @return a collection of {@link GrantedAuthority}, which contains the user's role.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> user.getRole());
    }

    /**
     * Returns the full name of the user.
     * 
     * @return the user's full name.
     */
    public String getFullname() {
        return user.getFullname();
    }

    /**
     * Returns the password used to authenticate the user.
     * 
     * @return the user's password.
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * Returns the username used to authenticate the user.
     * 
     * @return the user's username.
     */
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * Indicates whether the user's account has expired. This implementation always returns {@code true}.
     * 
     * @return {@code true} if the user's account is non-expired, {@code false} otherwise.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is locked or unlocked. This implementation always returns {@code true}.
     * 
     * @return {@code true} if the user's account is non-locked, {@code false} otherwise.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials (password) have expired. 
     * This implementation always returns {@code true}.
     * 
     * @return {@code true} if the user's credentials are non-expired, {@code false} otherwise.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is enabled or disabled. This implementation always returns {@code true}.
     * 
     * @return {@code true} if the user is enabled, {@code false} otherwise.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
