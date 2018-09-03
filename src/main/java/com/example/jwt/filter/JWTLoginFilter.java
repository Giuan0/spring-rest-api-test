package com.example.jwt.filter;

import com.example.jwt.service.MyUserPrincipal;
import com.example.jwt.service.TokenAuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    public JWTLoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException, IOException, ServletException {
        
        System.out.println("1 - attemptAuthentication");
        UserCredentials creds = new ObjectMapper().readValue(req.getInputStream(), UserCredentials.class);

        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        creds.getEmail(),
                        creds.getPassword(),
                        Collections.<GrantedAuthority>emptyList()
                )
        );
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest req,
            HttpServletResponse res, FilterChain chain,
            Authentication auth
    ) throws IOException, ServletException {
        System.out.println("3 - successfulAuthentication");
        MyUserPrincipal user = (MyUserPrincipal) auth.getPrincipal();
        TokenAuthenticationService.addAuthentication(res, user.getUsername(), user.getUserId());
    }

    @Override
    protected void unsuccessfulAuthentication(
            HttpServletRequest req,
            HttpServletResponse res, AuthenticationException failed
    ) throws IOException, ServletException {
        System.out.println(failed);
    }

}