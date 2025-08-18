package com.osh.backend.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

/**
 * JWT 토큰의 유효기간이 남았는지 확인하는 필터
 */
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 요청 Header에서 JWT토큰인 "Authorization" 추출
        String authorization = request.getHeader("Authorization");
        if(Objects.isNull(authorization) || authorization.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }


        String username = null;
        String token = null;
    }
}
