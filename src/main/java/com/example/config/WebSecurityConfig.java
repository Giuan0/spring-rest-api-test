package com.example.config;

import com.example.jwt.filter.JWTAuthenticationFilter;
import com.example.jwt.filter.JWTLoginFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    
	@Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        System.out.println("configure(HttpSecurity httpSecurity)");
        httpSecurity.csrf().disable().authorizeRequests()
			.antMatchers("/home").permitAll()
			.antMatchers(HttpMethod.POST, "/login").permitAll()
			.anyRequest().authenticated()
			.and()
			
			// filter login requests
			.addFilterBefore(new JWTLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
			
			// filter others requests, to verify the presence of jwt on header
			.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("-------------->>>>> ONLY ONCE - configure(AuthenticationManagerBuilder auth)");
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        // create default account
		// auth.inMemoryAuthentication()
		// 	.withUser("admin")
		// 	.password("{noop}password")
		// 	.roles("ADMIN");
    }

    @Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
}