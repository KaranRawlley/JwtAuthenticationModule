package com.mycompany.JwtAuthenticationModule.model;

public class JwtResponse {

	public JwtResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	private String jwtToken;

	public JwtResponse(String jwtToken) {
		super();
		this.jwtToken = jwtToken;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
	
	
	
}
