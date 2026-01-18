package com.app.ecomm.config;

import java.io.IOException;

import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.app.ecomm.repo.UserRepo;
import com.app.ecomm.service.CustomUserDetailsService;
import com.app.ecomm.service.JwtService;

import ch.qos.logback.classic.Logger;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired private CustomUserDetailsService service;
    @Autowired private JwtService jwtService;
    
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
                                   FilterChain filterChain) throws ServletException, IOException {
        
        String path = request.getServletPath();
        if (isPublicEndpoint(path)) {
            filterChain.doFilter(request, response);
            return;
        }
        
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                String email = jwtService.extractUsername(token);
                if (email != null && service.loadUserByUsername(email) != null) {
                    UserDetails user = service.loadUserByUsername(email);
                    UsernamePasswordAuthenticationToken authToken = 
                        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            } catch (Exception e) {
                // ✅ SILENT FAIL - Continue without auth
                System.err.println("JWT Error: " + e.getMessage());
            }
        }
        
        filterChain.doFilter(request, response); // ✅ Always continue!
    }

    
    private boolean isPublicEndpoint(String path) {
        return path.startsWith("/public") || 
               path.startsWith("/products") ||
               path.startsWith("/v3/api-docs") || 
               path.startsWith("/swagger-ui") || 
               path.startsWith("/swagger-resources");
    }
}
