package com.mycompany.JwtAuthenticationModule.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mycompany.JwtAuthenticationModule.service.CustomUserDetailsService;
import com.mycompany.JwtAuthenticationModule.util.JwtUtil;

//call this filter once per request 
@Controller
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	private CustomUserDetailsService customUserDetailService;
	
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//get the jwt token from request header	
		String bearerToken = request.getHeader("Authorization");
		String userName = null;
		String token = null;
		//validate retrived jwt token
		if(bearerToken != null && bearerToken.startsWith("Bearer ")) {
			
			//extract jwt token from bearerToken
			token = bearerToken.substring(7);
			
			
			try {
				//extract username from token
				userName = jwtUtil.extractUsername(token);
				//get details for this user
				UserDetails userDetails = customUserDetailService.loadUserByUsername(userName);
				
				//security checks
				
				if(userName!=null && SecurityContextHolder.getContext().getAuthentication() == null) {
					
					UsernamePasswordAuthenticationToken upat = 
							new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					
					SecurityContextHolder.getContext().setAuthentication(upat);
					
				}else {
					System.out.println("Invalid Token Format!!");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("Invalid Bearer Token Format!!");

		}
		//if all conditions are fine for token forward the filter request to requested endpoint
		filterChain.doFilter(request, response);
	}

	
}
