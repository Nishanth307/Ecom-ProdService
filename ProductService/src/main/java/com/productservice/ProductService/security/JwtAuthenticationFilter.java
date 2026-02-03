package com.productservice.ProductService.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final TokenValidator tokenValidator;

    public JwtAuthenticationFilter(TokenValidator tokenValidator) {
        this.tokenValidator = tokenValidator;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String token = extractTokenFromRequest(request);
        
        if (token != null) {
            Optional<JwtObject> jwtObject = tokenValidator.validateToken(token);
            
            if (jwtObject.isPresent()) {
                JwtObject jwt = jwtObject.get();
                
                // Extract roles
                List<SimpleGrantedAuthority> authorities = Collections.emptyList();
                if (jwt.getRoles() != null && !jwt.getRoles().isEmpty()) {
                    authorities = jwt.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole()))
                        .collect(Collectors.toList());
                }
                
                // Create authentication object
                UsernamePasswordAuthenticationToken authentication = 
                    new UsernamePasswordAuthenticationToken(
                        jwt.getUserId(), 
                        null, 
                        authorities
                    );
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                
                // Set authentication in security context
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        
        filterChain.doFilter(request, response);
    }

    private String extractTokenFromRequest(HttpServletRequest request) {
        // Try to get token from header
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        
        // Fallback to custom "token" header
        return request.getHeader("token");
    }
}

