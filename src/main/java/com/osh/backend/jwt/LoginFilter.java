package com.osh.backend.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.osh.backend.jwt.dto.CustomMemberDetails;
import com.osh.backend.jwt.dto.LoginRequest;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

/**
 * 로그인시 회원 이메일, 비밀번호 확인 후
 * 검증되면 JWT 토큰 생성 Class
 */
@Slf4j
@AllArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    private final JWTUtil jwtUtil;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try{
            LoginRequest loginRequest = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(),
                    loginRequest.getPassword()
            );

            return authenticationManager.authenticate(token);

        }catch (IOException e){
            log.debug("check : {}", e.getMessage());
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        CustomMemberDetails memberDetails = (CustomMemberDetails) authResult.getPrincipal();

        String username = memberDetails.getUsername();
        String role = memberDetails.getAuthorities().iterator().next().getAuthority();

        //JWT생성
        String token = jwtUtil.generateJwt(username, role);

        response.addHeader("Authorization", "Baarer " + token);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
//        response.getWriter().write();

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
    }

}
