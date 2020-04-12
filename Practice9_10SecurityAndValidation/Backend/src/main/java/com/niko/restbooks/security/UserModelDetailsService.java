package com.niko.restbooks.security;

import com.niko.restbooks.entities.UserEntity;
import com.niko.restbooks.services.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Authenticate a user from the database
 */
@Component("userDetailsService")
public class UserModelDetailsService implements UserDetailsService {

    private final UserService userService;

    public UserModelDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userService.findByLogin(username).orElseThrow(() ->
                new UsernameNotFoundException("No user for login: " + username));

        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(UserEntity user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        Stream.of(user.getActiveRole()).forEach(role -> authorities.add(new SimpleGrantedAuthority(role.name())));
        return authorities;
    }
}