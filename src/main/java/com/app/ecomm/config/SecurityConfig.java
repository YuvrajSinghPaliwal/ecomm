package com.app.ecomm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SecurityConfig {

	@Autowired
	private JwtFilter jwtFilter;
	
	@Bean
	public SecurityFilterChain sfc(HttpSecurity http) throws Exception{
		return http
				.cors(req->req.disable())
				.csrf(req->req.disable())
				.sessionManagement(req->req.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(req->req
						.requestMatchers("/public/**").permitAll()
						.requestMatchers("/sellers/**").hasRole("SELLER")
						.requestMatchers("/users/**").hasRole("CUSTOMER")
						.anyRequest().authenticated())
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
				.exceptionHandling(req->req
						.authenticationEntryPoint((reqs,res,entry)->res.sendError(401))
						.accessDeniedHandler((reqs,res,access)->res.sendError(403)))
				.build();
		
				
	}
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public RestTemplate template() {
		return new RestTemplate();
	}
	
}
