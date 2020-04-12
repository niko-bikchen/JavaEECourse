package com.niko.restbooks.security;

import com.niko.restbooks.entities.UserEntity;
import com.niko.restbooks.exceptions.NotFoundException;
import com.niko.restbooks.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Authenticate the user and collect user's authorities
 */
@Component
public class AuthenticationManager implements org.springframework.security.authentication.AuthenticationManager {

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException, NotFoundException {
        String username = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();

        UserEntity user = userService.findByLogin(username).orElseThrow(() -> new NotFoundException("No user for login: " + username));

        // collect user's roles and create authorities what user could access
        List<SimpleGrantedAuthority> authorities = Stream.of(user.getActiveRole())
                .map(authority -> new SimpleGrantedAuthority(authority.name()))
                .collect(Collectors.toList());

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, password, authorities);
        // authenticate this user
        SecurityContextHolder.getContext().setAuthentication(auth);
        return auth;
    }

}
