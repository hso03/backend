package com.osh.backend.jwt;

import com.osh.backend.jwt.dto.CustomMemberDetails;
import com.osh.backend.jwt.service.CustomMemberDetailsService;
import com.osh.backend.member.domain.Member;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

/**
 * JWT 토큰의 유효기간이 남았는지 확인하는 필터
 * 즉, JWT 유효성 검증 담당
 */
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 요청 Header에서 JWT토큰인 "Authorization" 추출
        String header = request.getHeader("Authorization");

        if(Objects.nonNull(header) && header.startsWith("Bearer ")){
            System.out.println("1");
            // Bearer이후 token 추출
            String token = header.split(" ")[1];
            if(Objects.nonNull(token) && !jwtUtil.isExpired(token)){
                System.out.println("2");
                String username = jwtUtil.getUsername(token);

                Member member = new Member(
                        username,
                        "temp",
                        "temp",
                        "temp"
                );

                CustomMemberDetails memberDetails = new CustomMemberDetails(member);
                Authentication authentication = new UsernamePasswordAuthenticationToken(memberDetails, null, memberDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);

            }
        }

        filterChain.doFilter(request, response);
    }
}
