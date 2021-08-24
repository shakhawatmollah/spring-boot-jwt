package com.shakhawat.jwt.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//Get user from database
		String pass = "$2a$12$zIDYYW3GEa8IjkAR2QfI3O.8RD7EVEaeriuHoa3Eaddb4.R/vkE7W"; // Bcrypt Hash of 123
		return new User("admin", pass, new ArrayList<>());
	}

}
