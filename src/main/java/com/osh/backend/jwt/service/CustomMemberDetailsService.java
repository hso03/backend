package com.osh.backend.jwt.service;

import com.osh.backend.advice.exception.MemberNotFoundException;
import com.osh.backend.jwt.dto.CustomMemberDetails;
import com.osh.backend.member.domain.Member;
import com.osh.backend.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomMemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByMemberEmail(username).orElseThrow(() -> new MemberNotFoundException("멤버를 찾을 수 없습니다."));
        return new CustomMemberDetails(member);
    }
}
