package com.mycompany.JwtAuthenticationModule.controller;

import java.security.Principal;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.JwtAuthenticationModule.entity.UserEntity;
import com.mycompany.JwtAuthenticationModule.model.JwtRequest;
import com.mycompany.JwtAuthenticationModule.model.JwtResponse;
import com.mycompany.JwtAuthenticationModule.model.UserModel;
import com.mycompany.JwtAuthenticationModule.service.CustomUserDetailsService;
import com.mycompany.JwtAuthenticationModule.util.JwtUtil;

@RestController
@RequestMapping("/api")
public class JwtController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> generateToken(@RequestBody JwtRequest jwtRequest){
		
		//authenticate the user
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUserName(), jwtRequest.getPassword()));
		
		UserDetails userDetails =  customUserDetailsService.loadUserByUsername(jwtRequest.getUserName());
		
		String token = jwtUtil.generateToken(userDetails);
		
		JwtResponse jwtResponse = new JwtResponse(token);
		
		return ResponseEntity.ok(jwtResponse);
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserModel> register(@RequestBody UserModel user){
		
		UserModel userModel = customUserDetailsService.register(user);
		
		ResponseEntity<UserModel> re = new ResponseEntity<>(userModel, HttpStatus.CREATED);
		//JwtResponse jwtResponse = new JwtResponse(userModel);
		
		return re;		
	}
	
	@GetMapping("/currentUser")
	public UserModel getCurrentUser(Principal principal) {
		UserDetails userDetail = customUserDetailsService.loadUserByUsername(principal.getName());
		
		return (UserModel) userDetail;
		
	}
	
}
