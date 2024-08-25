package com.pro_servises.pro.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.pro_servises.pro.config.JwtAuth.SECRET_KEY;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;


    public JwtAuthorizationFilter(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationToken = request.getHeader("Authorization");
        if (authorizationToken != null && authorizationToken.startsWith("Bearer ")) {
            try {
                String jwt = authorizationToken.substring(7);
                Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).build().parseClaimsJws(jwt).getBody();
                String username = claims.getSubject();
                System.out.println("youssef");
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
