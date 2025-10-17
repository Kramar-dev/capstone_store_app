package com.gd.storeapi.filter;

import com.gd.storeapi.service.JwtService;
import com.gd.storeapi.service.TokenContext;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        if (StringUtils.hasText(header) && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            try {
                String userId = jwtService.extractUserId(token);
                request.setAttribute("auth.userId", userId);
                request.setAttribute("auth.token", token);
                TokenContext.set(token, userId);
            } catch (Exception e) {
                // Optionally log or set error attribute; do not block unless required
            }
        }
        try {
            filterChain.doFilter(request, response);
        } finally {
            TokenContext.clear();
        }
    }
}