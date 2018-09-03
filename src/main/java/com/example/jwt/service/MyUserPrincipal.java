package com.example.jwt.service;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class MyUserPrincipal extends User {
    private Long userId;

    public MyUserPrincipal(
        String username, 
        String password, 
        List<GrantedAuthority> grantedAuthorities,
        Long userId) {
        
            super(username, password, grantedAuthorities);
            this.userId = userId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId){
        this.userId = this.userId;
    }

}