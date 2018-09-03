package com.example.jwt.service;

import java.util.Arrays;
import java.util.List;

import com.example.demo.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service   // It has to be annotated with @Service.
public class UserDetailsServiceImpl implements UserDetailsService  {
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	@Autowired
	UserRepository userRepository;
	
	@Override
	public MyUserPrincipal loadUserByUsername(String email) throws UsernameNotFoundException {

		System.out.println("2 - loadUserByUsername");
		com.example.demo.user.User user = this.userRepository.findByEmailAddress(email);

		List<GrantedAuthority> grantedAuthorities = AuthorityUtils// see how this works, no idea at all
		.commaSeparatedStringToAuthorityList("ROLE_ADMIN");

		return new MyUserPrincipal(user.getName(), user.getPassword(), grantedAuthorities, user.getId());
		
	}
	
	
}