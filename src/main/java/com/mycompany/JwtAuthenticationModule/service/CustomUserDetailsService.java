package com.mycompany.JwtAuthenticationModule.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

	//this method does the validation for user existence
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if(username.equals("John")) {//here Db call can be made instead of hardcoding.
			return new User("John", "12345678", new ArrayList<>());
		}else {
			throw new UsernameNotFoundException("User does not exists!");
		}
	}

	
}
