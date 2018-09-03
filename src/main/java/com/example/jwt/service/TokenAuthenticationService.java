package com.example.jwt.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.models.response.DefaultResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Date;

public class TokenAuthenticationService {
    
    private static final long EXPIRATIONTIME = 864000000;
    private static final String SECRET = "MySecreteApp";
    private static final String TOKEN_PREFIX = "Bearer";
    private static final String HEADER_STRING = "Authorization";

    public static void addAuthentication(HttpServletResponse res, String username, Long userId) {
        res.setHeader("Content-Type", "application/json;charset=UTF-8");
        
        String JWT = Jwts.builder()
                .setSubject(username)
                .setId(userId.toString())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        String token = TOKEN_PREFIX + " " + JWT;
        res.addHeader(HEADER_STRING, token);

        
        try {
            DefaultResponse<String> response = new DefaultResponse<String>(token, "user token", 200);
            ObjectMapper mapper = new ObjectMapper();
            String json =  mapper.writeValueAsString(response);

            res.getOutputStream().print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String genToken(String username, Long userId) {
        String JWT = Jwts.builder()
        .setSubject(username)
        .setId(userId.toString())
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
        .signWith(SignatureAlgorithm.HS512, SECRET)
        .compact();

        String token = TOKEN_PREFIX + " " + JWT;
        return token;
    }

    public static Authentication getByToken(String token) {
        String user = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody()
                .getSubject();
        // DefaultResponse response = new DefaultResponse<String>(user, "user token", 200)
        return user != null ? new UsernamePasswordAuthenticationToken(user, null, null) : null;
    }

    public static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            return getByToken(token);
        }
        return null;
    }
}