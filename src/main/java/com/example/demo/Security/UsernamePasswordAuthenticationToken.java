package com.example.demo.Security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UsernamePasswordAuthenticationToken extends AbstractAuthenticationToken {

    private final String username;
    private final String password;

    // Constructor for unauthenticated token
    public UsernamePasswordAuthenticationToken(String username, String password) {
        super(null); // Initially, no authorities are set
        this.username = username;
        this.password = password;
        setAuthenticated(false); // Token is not authenticated yet
    }

    // Constructor for authenticated token
    public UsernamePasswordAuthenticationToken(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.username = username;
        this.password = password;
        setAuthenticated(true); // Token is now authenticated
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public Object getCredentials() {
        return password; // Return password as credentials
    }

    @Override
    public Object getPrincipal() {
        return username; // Return username as principal
    }
}
