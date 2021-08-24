package com.shakhawat.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shakhawat.jwt.model.JWTRequest;
import com.shakhawat.jwt.model.JWTResponse;
import com.shakhawat.jwt.service.UserService;
import com.shakhawat.jwt.utility.JWTUtility;

@RestController
public class HomeController {
	
	@Autowired
	private JWTUtility jwtUtility;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String welcome() {
		return "Welcome to Spring Boot GWT";
	}
	
	@PostMapping("/authenticate")
	public JWTResponse authenticate(@RequestBody JWTRequest jwtRequest) throws Exception {
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
		} catch (BadCredentialsException exception) {
			throw new Exception("INVALID_CREDENTIALS", exception);
		}
		final UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getUsername());
		
		final String token = jwtUtility.generateToken(userDetails);
		
		return new JWTResponse(token);
	}
}
