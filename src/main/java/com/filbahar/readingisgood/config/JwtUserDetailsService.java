package com.filbahar.readingisgood.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author filbahar
 * @created 29.11.2021
 */

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final String testUsername = "test";
    private final String password = "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (testUsername.equals(username)) {
            return new User(username, password, new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
