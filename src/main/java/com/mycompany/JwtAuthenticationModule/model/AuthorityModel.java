package com.mycompany.JwtAuthenticationModule.model;

import org.springframework.security.core.GrantedAuthority;

public class AuthorityModel implements GrantedAuthority {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5524169612910192135L;
	
	private String authority;
	
	

	public AuthorityModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AuthorityModel(String authority) {
		super();
		this.authority = authority;
	}



	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	

}
