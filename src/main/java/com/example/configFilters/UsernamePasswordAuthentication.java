package com.example.configFilters;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;


public class UsernamePasswordAuthentication  extends UsernamePasswordAuthenticationToken {

    public UsernamePasswordAuthentication(
            Object principal,
            Object credentials,
            Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    public UsernamePasswordAuthentication(
            Object principal,
            Object credentials) {
        super(principal, credentials);
    }


}
