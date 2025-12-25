package com.app.ecomm.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.app.ecomm.repo.UserRepo;
import com.app.ecomm.service.CustomUserDetailsService;
import com.app.ecomm.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	private CustomUserDetailsService service;
	
	@Autowired
	private JwtService jwtService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String path=request.getServletPath();
		if(path.startsWith("/public")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		String authHeader=request.getHeader("Authorization");
		if(authHeader!=null&&authHeader.startsWith("Bearer ")) {
			String token=authHeader.substring(7);
			if(token!=null) {
				String email=jwtService.extractUsername(token);
				UserDetails user=service.loadUserByUsername(email);
				UsernamePasswordAuthenticationToken authToken=
						new UsernamePasswordAuthenticationToken(user ,null,user.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		filterChain.doFilter(request, response);
		
	}

	
	
}
