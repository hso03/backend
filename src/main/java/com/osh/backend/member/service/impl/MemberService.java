package com.osh.backend.member.service.impl;

import com.osh.backend.member.domain.Member;
import com.osh.backend.member.dto.MemberRegisterRequest;
import com.osh.backend.member.dto.MemberResponse;
import com.osh.backend.member.dto.MemberUpdateRequest;
import com.osh.backend.member.repository.MemberRepository;
import com.osh.backend.member.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService implements MemberServiceImpl {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public MemberResponse registerMember(MemberRegisterRequest memberRegisterRequest) {
        String encodedPassword = passwordEncoder.encode(memberRegisterRequest.getMemberPassword());

        Member member = Member.builder()
                        .memberEmail(memberRegisterRequest.getMemberEmail())
                        .memberPassword(encodedPassword)
                        .memberName(memberRegisterRequest.getMemberName())
                        .memberMobile(memberRegisterRequest.getMemberMobile())
                        .build();

        Member savedMember = memberRepository.save(member);

        return MemberResponse.builder()
                .memberNo(savedMember.getMemberNo())
                .memberEmail(savedMember.getMemberEmail())
                .memberName(savedMember.getMemberName())
                .memberMobile(savedMember.getMemberMobile())
                .memberCreatedAt(savedMember.getMemberCreatedAt())
                .memberWithdrawalAt(savedMember.getMemberWithdrawalAt())
                .build();
    }

    @Override
    public MemberResponse getMember(String memberEmail) {
        return null;
    }

    @Override
    public MemberResponse getMember(Long memberNo) {
        return null;
    }

    @Override
    public MemberResponse updateMember(MemberUpdateRequest memberUpdateRequest) {
        return null;
    }

    @Override
    public MemberResponse withdrawalMember(String memberEmail) {
        return null;
    }

    @Override
    public MemberResponse withdrawalMember(Long memberNo) {
        return null;
    }

    @Override
    public boolean login(String memberEmail, String memberPassword) {
        Member member = memberRepository.findByMemberEmail(memberEmail)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));

        if (passwordEncoder.matches(memberPassword, member.getMemberPassword())) {
            return true; // 비밀번호 일치시 true
        } else {
            // 비밀번호 불일치시 Exception
            throw new RuntimeException("비밀번호가 올바르지 않습니다.");
        }
    }

}
